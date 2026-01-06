
import React, { useEffect, useMemo, useState } from 'react'
import Navbar from '../components/Navbar.jsx'
import StatCard from '../components/StatCard.jsx'
import TabNav from '../components/TabNav.jsx'
import StatusBadge from '../components/StatusBadge.jsx'
import Table from '../components/Table.jsx'
import Calendar from '../components/Calendar.jsx'
import SessionSelector from '../components/SessionSelector.jsx'
import Toast from '../components/Toast.jsx'
import { getMyLeaveBalance, getMyLeaves, applyLeave, cancelLeave } from '../api/leaves.js'
import { rangeDates } from '../utils/date.js'
import { required, minLen, validateDateRange } from '../utils/validators.js'

export default function EmployeeDashboard(){
  const [active, setActive] = useState('balance')
  const [balance, setBalance] = useState({})
  const [leaves, setLeaves] = useState([])
  const [toast, setToast] = useState(null)
  const [type, setType] = useState('')
  const [range, setRange] = useState({})
  const [breakdown, setBreakdown] = useState([])
  const [reason, setReason] = useState('')

  useEffect(() => { (async () => { try { setBalance(await getMyLeaveBalance()); setLeaves(await getMyLeaves()) } catch(e){} })() }, [])

  const pendingCount = useMemo(()=>leaves.filter(l=>l.status==='PENDING').length, [leaves])
  const approvedCount = useMemo(()=>leaves.filter(l=>l.status==='APPROVED').length, [leaves])

  useEffect(() => {
    if(range.start && range.end){
      const dates = rangeDates(range.start, range.end).filter(d => { const wd = new Date(d).getDay(); return wd !== 0 && wd !== 6 })
      setBreakdown(dates.map(d => ({ date: d, session: 'FULL' })))
    } else setBreakdown([])
  }, [range])

  const totalDays = breakdown.reduce((acc, d)=> acc + (d.session==='FULL' ? 1 : 0.5), 0)

  async function submitLeave(){
    const err = validateDateRange(range.start, range.end) || required(type) || minLen(reason, 10)
    if(err){ setToast({msg:err, type:'error'}); return }
    const available = (balance[type] && balance[type].available) ?? Infinity
    if(totalDays > available){ setToast({msg:`Selected ${totalDays} days exceeds available ${available} days for ${type}`, type:'error'}); return }
    try{
      const lr = await applyLeave({ type, startDate: range.start, endDate: range.end, reason, breakdown })
      setLeaves(prev => [lr, ...prev])
      setActive('history')
      setToast({msg:'Leave request submitted', type:'success'})
      setType(''); setRange({}); setReason('')
    }catch(e){ setToast({msg: e?.response?.data?.message || 'Failed to submit', type:'error'}) }
  }

  async function onCancel(id){
    try{ await cancelLeave(id); setLeaves(prev => prev.map(l=>l.id===id ? {...l, status:'CANCELLED'} : l)); setToast({msg:'Leave cancelled', type:'success'}) }
    catch{ setToast({msg:'Failed to cancel', type:'error'}) }
  }

  return (
    <div className="container">
      <Navbar />
      <div className="grid grid-3" style={{marginBottom:16}}>
        <StatCard title="Total Requests" subtitle="All time leave requests" value={leaves.length}/>
        <StatCard title="Pending" subtitle="Awaiting manager approval" value={pendingCount} tone="warning"/>
        <StatCard title="Approved" subtitle="Approved leave requests" value={approvedCount} tone="success"/>
      </div>

      <div className="card vstack">
        <h3>Leave Management</h3>
        <TabNav tabs={[{ key:'balance', label:'Leave Balance' }, { key:'apply', label:'Apply Leave' }, { key:'history', label:'Leave History' }]} active={active} onChange={(k)=>setActive(k)} />

        {active==='balance' && (
          <div className="grid grid-2" style={{marginTop:16}}>
            {['ANNUAL','SICK','CASUAL','PARENTAL'].map(k => {
              const b = balance[k] || { total: 0, used: 0, available: 0 }
              const name = k==='ANNUAL' ? 'Annual Leave' : k==='SICK' ? 'Sick Leave' : k==='CASUAL' ? 'Casual Leave' : 'Maternity/Paternity Leave'
              return (
                <div key={k} className="card vstack">
                  <strong>{name}</strong>
                  <div className="hstack" style={{justifyContent:'space-between'}}>
                    <span>Available</span><span style={{fontWeight:800}}>{b.available} days</span>
                  </div>
                  <div style={{height:8, background:'#111827', borderRadius:999}}></div>
                  <div className="hstack" style={{justifyContent:'space-between', color:'#6B7280'}}>
                    <span>Total {b.total}</span>
                    <span>Used <span style={{color:'var(--danger)'}}>{b.used}</span></span>
                    <span>Available <span style={{color:'var(--success)'}}>{b.available}</span></span>
                  </div>
                </div>
              )
            })}
          </div>
        )}

        {active==='apply' && (
          <div className="vstack" style={{marginTop:16}}>
            <div className="card" style={{background:'#F9FAFB'}}>
              <strong>How to apply for leave</strong>
              <ul>
                <li>Select a leave type from your available balance</li>
                <li>Click on a start date, then click an end date in the calendar</li>
                <li>Customize session (Full/First Half/Second Half) for each selected day</li>
                <li>Weekends and past dates are automatically excluded</li>
              </ul>
            </div>

            <label className="label">Leave Type *</label>
            <select className="select" value={type} onChange={e=>setType(e.target.value)}>
              <option value="">Select leave type</option>
              <option value="ANNUAL">Annual Leave</option>
              <option value="SICK">Sick Leave</option>
              <option value="CASUAL">Casual Leave</option>
              <option value="PARENTAL">Maternity/Paternity Leave</option>
            </select>

            <h4>Select Dates and Sessions</h4>
            <Calendar value={range} onChange={setRange} />

            {breakdown.length>0 && (
              <div className="vstack">
                <small style={{color:'#6B7280'}}>Total days (considering sessions): <strong>{totalDays}</strong></small>
                {breakdown.map((d, i)=>(
                  <SessionSelector key={i} date={d.date} value={d.session} onChange={(s)=>setBreakdown(prev => prev.map(x => x.date===d.date ? {...x, session:s} : x))} />
                ))}
              </div>
            )}

            <label className="label">Reason for Leave *</label>
            <textarea className="textarea" rows={3} value={reason} onChange={e=>setReason(e.target.value)} placeholder="Please provide a reason for your leave request"></textarea>

            <div className="hstack" style={{justifyContent:'flex-end'}}>
              <button className="btn ghost" onClick={() => { setType(''); setRange({}); setBreakdown([]); setReason('') }}>Reset</button>
              <button className="btn primary" onClick={submitLeave}>Submit Leave Request</button>
            </div>
          </div>
        )}

        {active==='history' && (
          <div className="vstack" style={{marginTop:16}}>
            <h4>Leave History</h4>
            <Table columns={['Leave Type','Dates','Days','Status','Applied On','Actions']}
              rows={leaves.map(l => ([
                l.type==='ANNUAL'?'Annual Leave':l.type==='SICK'?'Sick Leave':l.type==='CASUAL'?'Casual Leave':'Maternity/Paternity',
                `${l.startDate} - ${l.endDate}`,
                l.days,
                <StatusBadge status={l.status} />,
                l.appliedOn,
                <div className="hstack">
                  <button className="btn ghost" aria-label="View">👁</button>
                  {l.status==='PENDING' && <button className="btn danger" onClick={()=>onCancel(l.id)}>Cancel</button>}
                </div>
              ]))}
            />
          </div>
        )}
      </div>
      {toast && <Toast message={toast.msg} type={toast.type}/>}
    </div>
  )
}
