import request from '@/utils/request'

// 查询有害生物列表
export function listPests(query) {
  return request({
    url: '/pests/pests/list',
    method: 'get',
    params: query
  })
}

// 查询有害生物详细
export function getPests(id) {
  return request({
    url: '/pests/pests/' + id,
    method: 'get'
  })
}

export function getCounttime(){
  return request({
    url: '/pests/pests/getCounttime',
    method: 'get'
  })
}

export function getCountNature(){
  return request({
    url: '/pests/pests/getCountNature',
    method: 'get'
  })
}

export function getCounttype(){
  return request({
    url: '/pests/pests/getCounttype',
    method: 'get'
  })
}



export function getCountnumber(){
  return request({
    url: '/pests/pests/getCountnumber',
    method: 'get'
  })
}

// 新增有害生物
export function addPests(data) {
  return request({
    url: '/pests/pests',
    method: 'post',
    data: data
  })
}

// 修改有害生物
export function updatePests(data) {
  return request({
    url: '/pests/pests',
    method: 'put',
    data: data
  })
}

// 删除有害生物
export function delPests(id) {
  return request({
    url: '/pests/pests/' + id,
    method: 'delete'
  })
}
