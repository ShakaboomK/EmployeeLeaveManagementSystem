
import { http } from './http.js'
export async function login(username, password){
  const { data } = await http.post('/auth/login', { username, password })
  localStorage.setItem('token', data.token)
  return data.user
}
export function logout(){ localStorage.removeItem('token') }
export async function me(){ const { data } = await http.get('/auth/me'); return data }
