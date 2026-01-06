
import React from 'react'
export default function TabNav({ tabs, active, onChange }){
  return (
    <div className="tabbar" role="tablist" aria-label="Sections">
      {tabs.map(t => (
        <button key={t.key} role="tab" aria-selected={active===t.key} className={`tab ${active===t.key ? 'active' : ''}`} onClick={()=>onChange(t.key)}>
          {t.label} {t.badge ? <span className="badge readonly" style={{marginLeft:8}}>{t.badge}</span> : null}
        </button>
      ))}
    </div>
  )
}
