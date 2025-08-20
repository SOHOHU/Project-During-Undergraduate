import request from '@/utils/request'

// 查询踏查路线列表
export function listRoutes(query) {
  return request({
    url: '/routes/routes/list',
    method: 'get',
    params: query
  })
}

// 查询踏查路线详细
export function getRoutes(id) {
  return request({
    url: '/routes/routes/' + id,
    method: 'get'
  })
}

// 新增踏查路线
export function addRoutes(data) {
  return request({
    url: '/routes/routes',
    method: 'post',
    data: data
  })
}

// 修改踏查路线
export function updateRoutes(data) {
  return request({
    url: '/routes/routes',
    method: 'put',
    data: data
  })
}

// 删除踏查路线
export function delRoutes(id) {
  return request({
    url: '/routes/routes/' + id,
    method: 'delete'
  })
}
