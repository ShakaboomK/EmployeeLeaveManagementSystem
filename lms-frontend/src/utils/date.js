
import dayjs from 'dayjs'
import isBetween from 'dayjs/plugin/isBetween'
dayjs.extend(isBetween)
export const today = () => dayjs().startOf('day')
export const toISO = (d) => dayjs(d).format('YYYY-MM-DD')
export const isWeekend = (d) => { const w = d.day(); return w === 0 || w === 6 }
export const rangeDates = (startISO, endISO) => {
  const s = dayjs(startISO), e = dayjs(endISO)
  const arr = []
  for (let d = s; !d.isAfter(e, 'day'); d = d.add(1, 'day')) arr.push(toISO(d))
  return arr
}
