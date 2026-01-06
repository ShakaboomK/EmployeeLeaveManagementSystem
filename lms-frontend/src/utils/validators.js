
export const required = (v) => (!v || !String(v).trim()) ? 'Required' : null
export const minLen = (v, n) => (String(v).trim().length < n) ? `Minimum ${n} characters` : null
export function validateDateRange(start, end){
  if(!start || !end) return 'Select start and end date'
  if(start > end) return 'Start date must be before end date'
  return null
}
