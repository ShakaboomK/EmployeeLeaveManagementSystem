
import React, { useEffect } from 'react'
export default function Modal({ open, title, children, onClose }){
  useEffect(() => {
    if(open){
      const onEsc = (e) => { if(e.key==='Escape') onClose() }
      window.addEventListener('keydown', onEsc); return () => window.removeEventListener('keydown', onEsc)
    }
  }, [open])
  if(!open) return null
  return (
    <div aria-hidden="true" style={{position:'fixed', inset:0, background:'rgba(0,0,0,.45)', display:'flex', alignItems:'center', justifyContent:'center', zIndex:50}}>
      <div className="card" role="dialog" aria-modal="true" aria-label={title} style={{maxWidth:560}}>
        <div className="hstack" style={{justifyContent:'space-between'}}>
          <h3>{title}</h3>
          <button aria-label="Close" className="btn ghost" onClick={onClose}>✕</button>
        </div>
        <div className="vstack">{children}</div>
      </div>
    </div>
  )
}
