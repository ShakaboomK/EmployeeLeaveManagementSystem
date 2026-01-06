
import { http } from './http.js'
export async function getMyLeaveBalance(){ const { data } = await http.get('/leaves/balance'); return data }
export async function getMyLeaves(){ const { data } = await http.get('/leaves/me'); return data }
export async function applyLeave(payload){ const { data } = await http.post('/leaves', payload); return data }
export async function cancelLeave(id){ await http.post(`/leaves/${id}/cancel`) }
export async function managerPending(){ const { data } = await http.get('/leaves/pending'); return data }
export async function approve(id, comments){ const { data } = await http.post(`/leaves/${id}/approve`, { comments }); return data }
export async function reject(id, comments){ const { data } = await http.post(`/leaves/${id}/reject`, { comments }); return data }
export async function adminAllRequests(){ const { data } = await http.get('/leaves/all'); return data }
