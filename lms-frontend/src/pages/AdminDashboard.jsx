
import React, { useEffect, useState } from 'react'
import Navbar from '../components/Navbar.jsx'
import TabNav from '../components/TabNav.jsx'
import Table from '../components/Table.jsx'
import StatusBadge from '../components/StatusBadge.jsx'
import { listUsers, createUser } from '../api/users.js'
import { listEmployees, assignManager, listLeaveTypes, addLeaveType } from '../api/org.js'
import { adminAllRequests } from '../api/leaves.js'

export default function AdminDashboard(){
  const [active, setActive] = useState('employees')
  const [users, setUsers] = useState([])
  const [employees, setEmployees] = useState([])
  const [leaveTypes, setLeaveTypes] = useState([])
  const [requests, setRequests] = useState([])

  useEffect(()=>{ (async()=>{
    try{
      setUsers(await listUsers())
      setEmployees(await listEmployees())
      setLeaveTypes(await listLeaveTypes())
      setRequests(await adminAllRequests())
    }catch{}
  })() }, [])

  return (
    <div className="container">
      <Navbar />
      <div className="card vstack">
        <h3>System Administration</h3>
        <TabNav tabs={[{ key:'users', label:'Users' },{ key:'employees', label:'Employees' },{ key:'types', label:'Leave Types' },{ key:'requests', label:'Leave Requests' }]} active={active} onChange={(k)=>setActive(k)} />

        {active==='employees' && (
          <div className="vstack" style={{marginTop:16}}>
            <h4>Employee Records</h4>
            <div className="hstack" style={{justifyContent:'flex-end'}}>
              <button className="btn accent">Create Employee</button>
            </div>
            <Table columns={['Employee Code','Name','Department','Manager','Join Date','Actions']}
              rows={employees.map((e)=>[
                e.code, e.name, e.department, e.managerName || 'None', e.joinDate,
                <button className="btn ghost" onClick={()=>assignManager(e.id, prompt('Manager ID') || '')}>Assign Manager</button>
              ])}
            />
          </div>
        )}

        {active==='requests' && (
          <div className="vstack" style={{marginTop:16}}>
            <div className="card" style={{background:'#F9FAFB'}}>
              <strong>Note:</strong> Admins can view all leave requests but cannot approve or reject them. Only managers can approve/reject leave requests from their team members.
            </div>
            <Table columns={['Employee','Leave Type','Start','End','Days','Reason','Status','Applied On']}
              rows={requests.map((r)=>[
                r.employeeName,
                r.type,
                r.startDate,
                r.endDate,
                r.days,
                r.reason,
                <StatusBadge status={r.status}/>,
                r.appliedOn
              ])}
            />
          </div>
        )}

        {active==='users' && (
          <div className="vstack" style={{marginTop:16}}>
            <div className="hstack" style={{justifyContent:'flex-end'}}>
              <button className="btn accent" onClick={async ()=>{
                const username = prompt('Username') || ''
                const email = prompt('Email') || ''
                const role = (prompt('Role (ADMIN/MANAGER/EMPLOYEE)') || 'EMPLOYEE').toUpperCase()
                const password = prompt('Password') || 'welcome123'
                if(username){ const u = await createUser({ username, email, role, password }); setUsers(prev=>[u,...prev]) }
              }}>Create User</button>
            </div>
            <Table columns={['Username','Email','Role','Created At']}
              rows={users.map((u)=>[u.username, u.email, <span className={`badge ${u.role==='ADMIN'?'rejected':u.role==='MANAGER'?'approved':'readonly'}`}>{u.role}</span>, u.createdAt || '' ])}
            />
          </div>
        )}

        {active==='types' && (
          <div className="vstack" style={{marginTop:16}}>
            <div className="hstack" style={{justifyContent:'flex-end'}}>
              <button className="btn accent" onClick={async ()=>{
                const name = prompt('Leave type name') || ''
                const key = prompt('Key (ANNUAL/SICK/CASUAL/PARENTAL)') || ''
                const yearlyLimit = Number(prompt('Yearly limit')) || 0
                const description = prompt('Description') || ''
                if(name && key){ const lt = await addLeaveType({ name, key, yearlyLimit, description }); setLeaveTypes(prev=>[lt,...prev]) }
              }}>Add Leave Type</button>
            </div>
            <Table columns={['Leave Type','Yearly Limit','Description']}
              rows={leaveTypes.map((t)=>[t.name, t.yearlyLimit, t.description])}
            />
          </div>
        )}
      </div>
    </div>
  )
}
