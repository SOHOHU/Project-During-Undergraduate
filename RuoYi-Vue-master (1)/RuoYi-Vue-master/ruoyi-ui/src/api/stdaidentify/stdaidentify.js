import request from '@/utils/request'

// 查询标准地鉴定列表
export function listStdaidentify(query) {
  return request({
    url: '/stdaidentify/stdaidentify/list',
    method: 'get',
    params: query
  })
}

// 查询标准地鉴定详细
export function getStdaidentify(id) {
  return request({
    url: '/stdaidentify/stdaidentify/' + id,
    method: 'get'
  })
}

// 新增标准地鉴定
export function addStdaidentify(data) {
  return request({
    url: '/stdaidentify/stdaidentify',
    method: 'post',
    data: data
  })
}

// 修改标准地鉴定
export function updateStdaidentify(data) {
  return request({
    url: '/stdaidentify/stdaidentify',
    method: 'put',
    data: data
  })
}

// 删除标准地鉴定
export function delStdaidentify(id) {
  return request({
    url: '/stdaidentify/stdaidentify/' + id,
    method: 'delete'
  })
}
