
import { http } from './http.js'
export async function listUsers(){ const { data } = await http.get('/admin/users'); return data }
export async function createUser(payload){ const { data } = await http.post('/admin/users', payload); return data }
