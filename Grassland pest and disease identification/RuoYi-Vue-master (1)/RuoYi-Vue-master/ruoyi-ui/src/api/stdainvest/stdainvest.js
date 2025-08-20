import request from '@/utils/request'

// 查询标准地调查列表
export function listStdainvest(query) {
  return request({
    url: '/stdainvest/stdainvest/list',
    method: 'get',
    params: query
  })
}

// 查询标准地调查详细
export function getStdainvest(id) {
  return request({
    url: '/stdainvest/stdainvest/' + id,
    method: 'get'
  })
}

// 新增标准地调查
export function addStdainvest(data) {
  return request({
    url: '/stdainvest/stdainvest',
    method: 'post',
    data: data
  })
}

// 修改标准地调查
export function updateStdainvest(data) {
  return request({
    url: '/stdainvest/stdainvest',
    method: 'put',
    data: data
  })
}

// 删除标准地调查
export function delStdainvest(id) {
  return request({
    url: '/stdainvest/stdainvest/' + id,
    method: 'delete'
  })
}
