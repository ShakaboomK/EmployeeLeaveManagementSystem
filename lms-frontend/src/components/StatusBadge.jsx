
import React from 'react'
export default function StatusBadge({ status }){
  const cls = status==='APPROVED' ? 'approved' : status==='REJECTED' ? 'rejected' : status==='PENDING' ? 'pending' : 'readonly'
  return <span className={`badge ${cls}`}>{status}</span>
}
