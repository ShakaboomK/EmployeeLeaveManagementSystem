
import React from 'react'
export default function Toast({ message, type='info' }){
  const bg = type==='success' ? 'var(--success)' : type==='error' ? 'var(--danger)' : '#374151'
  return (
    <div role="status" style={{position:'fixed', bottom:24, right:24, background:bg, color:'#fff', padding:'12px 16px', borderRadius:10, boxShadow:'var(--shadow)'}}>
      {message}
    </div>
  )
}
