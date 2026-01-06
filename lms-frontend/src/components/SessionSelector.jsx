
import React from 'react'
export default function SessionSelector({ date, value, onChange }){
  return (
    <div className="hstack" style={{justifyContent:'space-between', border:'1px solid var(--border)', borderRadius:10, padding:10}}>
      <strong>{date}</strong>
      <div className="hstack" role="group" aria-label={`Session for ${date}`}>
        {['FULL','FIRST_HALF','SECOND_HALF'].map(s => (
          <label key={s} className="hstack" style={{gap:8}}>
            <input type="radio" name={`session-${date}`} checked={value===s} onChange={()=>onChange(s)}/>
            {s==='FULL' ? 'Full Day' : s==='FIRST_HALF' ? 'First Half' : 'Second Half'}
          </label>
        ))}
      </div>
    </div>
  )
}
