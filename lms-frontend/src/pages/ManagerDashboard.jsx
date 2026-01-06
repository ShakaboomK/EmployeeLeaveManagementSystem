
import React, { useEffect, useState } from 'react'
import Navbar from '../components/Navbar.jsx'
import StatCard from '../components/StatCard.jsx'
import TabNav from '../components/TabNav.jsx'
import Table from '../components/Table.jsx'
import StatusBadge from '../components/StatusBadge.jsx'
import Modal from '../components/Modal.jsx'
import { managerPending, approve, reject } from '../api/leaves.js'

export default function ManagerDashboard(){
  const [active, setActive] = useState('pending')
  const [pending, setPending] = useState([])
  const [selected, setSelected] = useState(null)
  useEffect(()=>{ (async()=>{ setPending(await managerPending()) })() }, [])
  async function handleApprove(l){ const res = await approve(l.id); setPending(p => p.filter(x => x.id !== l.id)); setSelected(res) }
  async function handleReject(l){ const res = await reject(l.id); setPending(p => p.filter(x => x.id !== l.id)); setSelected(res) }
  return (
    <div className="container">
      <Navbar />
      <div className="grid grid-3" style={{marginBottom:16}}>
        <StatCard title="Team Size" subtitle="Total team members reporting to you" value={2}/>
        <StatCard title="Pending Approvals" subtitle="Leave requests awaiting your decision" value={pending.length} tone="warning"/>
      </div>
      <div className="card vstack">
        <h3>Team Management</h3>
        <TabNav tabs={[{ key:'team', label:'My Team' },{ key:'pending', label:'Pending Requests', badge: pending.length }]} active={active} onChange={(k)=>setActive(k)} />
        {active==='pending' && (
          <div className="vstack" style={{marginTop:16}}>
            <h4>Pending Leave Requests</h4>
            <Table columns={['Employee','Leave Type','Dates','Days','Applied On','Actions']}
              rows={pending.map(l => ([
                l.employeeName,
                <span className="badge readonly">{l.type==='ANNUAL'?'Annual':'Sick'}</span>,
                `${l.startDate} - ${l.endDate}`,
                l.days,
                l.appliedOn,
                <div className="hstack">
                  <button className="btn ghost" onClick={()=>setSelected(l)} aria-label="View">👁</button>
                  <button className="btn primary" onClick={()=>handleApprove(l)}>Approve</button>
                  <button className="btn danger" onClick={()=>handleReject(l)}>Reject</button>
                </div>
              ]))}
            />
          </div>
        )}
        {active==='team' && (
          <div className="vstack" style={{marginTop:16}}>
            <h4>Team Members</h4>
            <Table columns={['Employee Code','Name','Department','Join Date','Status']}
              rows={[
                ['EMP001','Alice Smith','Engineering','20/03/2023', <span className="badge approved">Active</span>],
                ['EMP002','Bob Johnson','Engineering','10/06/2023', <span className="badge approved">Active</span>],
              ]}
            />
          </div>
        )}
      </div>
      <Modal open={!!selected} title="Leave Request Details" onClose={()=>setSelected(null)}>
        {selected && (<>
          <div className="grid grid-2">
            <div><strong>Leave Type</strong><br/>{selected.type}</div>
            <div><strong>Status</strong><br/><StatusBadge status={selected.status}/></div>
            <div><strong>Start Date</strong><br/>{selected.startDate}</div>
            <div><strong>End Date</strong><br/>{selected.endDate}</div>
            <div><strong>Total Days</strong><br/>{selected.days}</div>
            <div><strong>Applied On</strong><br/>{selected.appliedOn}</div>
          </div>
          <div><strong>Reason</strong><br/>{selected.reason}</div>
          <div>
            <strong>Leave Days Breakdown</strong>
            <div className="vstack">
              {(selected.breakdown || []).map(d => (
                <div key={d.date} className="hstack" style={{justifyContent:'space-between'}}>
                  <span>{d.date}</span>
                  <span className="badge readonly">{d.session==='FULL'?'Full Day':d.session==='FIRST_HALF'?'First Half':'Second Half'}</span>
                </div>
              ))}
            </div>
          </div>
        </>)}
      </Modal>
    </div>
  )
}
