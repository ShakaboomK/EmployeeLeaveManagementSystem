
import React, { useMemo, useState } from 'react'
import dayjs from 'dayjs'
import isBetween from 'dayjs/plugin/isBetween'
dayjs.extend(isBetween)
const today = () => dayjs().startOf('day')
const toISO = d => dayjs(d).format('YYYY-MM-DD')
const isWeekend = d => { const w = d.day(); return w === 0 || w === 6 }

export default function Calendar({ value, onChange }){
  const [month, setMonth] = useState(dayjs())
  const start = value.start ? dayjs(value.start) : null
  const end = value.end ? dayjs(value.end) : null

  const grid = useMemo(()=>{
    const startOfMonth = month.startOf('month')
    const endOfMonth = month.endOf('month')
    const startGrid = startOfMonth.startOf('week')
    const endGrid = endOfMonth.endOf('week')
    const days = []
    for(let d = startGrid; d.isBefore(endGrid) || d.isSame(endGrid,'day'); d = d.add(1,'day')) days.push(d)
    return days
  }, [month])

  function select(d){
    if(d.isBefore(today(), 'day')) return
    if(isWeekend(d)) return
    if(!start || (start && end)) onChange({ start: toISO(d), end: undefined })
    else if(d.isBefore(start, 'day')) onChange({ start: toISO(d), end: start.format('YYYY-MM-DD') })
    else onChange({ start: start.format('YYYY-MM-DD'), end: toISO(d) })
  }

  const inRange = d => start && end && (d.isSame(start,'day') || d.isSame(end,'day') || (d.isAfter(start,'day') && d.isBefore(end,'day')))

  return (
    <div className="card vstack">
      <div className="hstack" style={{justifyContent:'space-between'}}>
        <strong>{month.format('MMMM YYYY')}</strong>
        <div className="hstack">
          <button className="btn ghost" onClick={()=>setMonth(m=>m.subtract(1,'month'))}>‹</button>
          <button className="btn ghost" onClick={()=>setMonth(dayjs())}>Today</button>
          <button className="btn ghost" onClick={()=>setMonth(m=>m.add(1,'month'))}>›</button>
        </div>
      </div>
      <div style={{display:'grid', gridTemplateColumns:'repeat(7,1fr)', gap:8}}>
        {['Sun','Mon','Tue','Wed','Thu','Fri','Sat'].map(d=>
          <div key={d} style={{textAlign:'center', color:'#6B7280', fontWeight:600}}>{d}</div>
        )}
        {grid.map((d,i)=>{
          const disabled = d.isBefore(today(), 'day') || isWeekend(d) || d.month() !== month.month()
          const isToday = d.isSame(today(),'day')
          const selected = !disabled && inRange(d)
          return (
            <button key={i} onClick={()=>select(d)} aria-label={d.format('DD MMM YYYY')}
              className="btn ghost"
              style={{
                padding:'14px', borderRadius:10, background: selected ? '#E0EAFF' : '#fff',
                border: isToday ? '2px solid #4F46E5' : '1px solid var(--border)',
                color: disabled ? '#9CA3AF' : '#111827', cursor: disabled ? 'not-allowed':'pointer'
              }}>
              {d.date()}
            </button>
          )
        })}
      </div>
      <div className="legend">
        <span className="dot" style={{background:'#4F46E5'}}></span> Today
        <span className="dot" style={{background:'#E0EAFF'}}></span> Selected
        <span className="dot" style={{background:'#F3F4F6'}}></span> Disabled
      </div>
    </div>
  )
}
