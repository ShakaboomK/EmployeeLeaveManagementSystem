
import { http } from './http.js'
export async function listEmployees(){ const { data } = await http.get('/admin/employees'); return data }
export async function assignManager(empId, mgrId){ const { data } = await http.post(`/admin/employees/${empId}/assign-manager`, { managerId: mgrId }); return data }
export async function listLeaveTypes(){ const { data } = await http.get('/admin/leave-types'); return data }
export async function addLeaveType(payload){ const { data } = await http.post('/admin/leave-types', payload); return data }
