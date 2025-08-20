import request from '@/utils/request'

// 查询路线审批列表
export function listRouteapplys(query) {
  return request({
    url: '/routeapplys/routeapplys/list',
    method: 'get',
    params: query
  })
}

// 查询路线审批详细
export function getRouteapplys(id) {
  return request({
    url: '/routeapplys/routeapplys/' + id,
    method: 'get'
  })
}

// 新增路线审批
export function addRouteapplys(data) {
  return request({
    url: '/routeapplys/routeapplys',
    method: 'post',
    data: data
  })
}

// 修改路线审批
export function updateRouteapplys(data) {
  return request({
    url: '/routeapplys/routeapplys',
    method: 'put',
    data: data
  })
}

// 删除路线审批
export function delRouteapplys(id) {
  return request({
    url: '/routeapplys/routeapplys/' + id,
    method: 'delete'
  })
}
