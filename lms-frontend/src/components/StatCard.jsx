
import React from 'react'
export default function StatCard({ title, subtitle, value, tone='default' }){
  const color = tone==='warning' ? 'var(--warning)' : tone==='success' ? 'var(--success)' : 'var(--text)'
  return (
    <div className="card vstack" role="group" aria-label={title}>
      <div style={{color:'#374151'}}>{title}<br/><small style={{color:'#6B7280'}}>{subtitle}</small></div>
      <div style={{fontSize:32, fontWeight:800, color}}>{value}</div>
    </div>
  )
}
