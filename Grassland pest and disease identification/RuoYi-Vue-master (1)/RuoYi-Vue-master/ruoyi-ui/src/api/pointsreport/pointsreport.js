import request from '@/utils/request'

// 查询踏查点上报列表
export function listPointsreport(query) {
  return request({
    url: '/pointsreport/pointsreport/list',
    method: 'get',
    params: query
  })
}

// 查询踏查点上报详细
export function getPointsreport(id) {
  return request({
    url: '/pointsreport/pointsreport/' + id,
    method: 'get'
  })
}

// 新增踏查点上报
export function addPointsreport(data) {
  return request({
    url: '/pointsreport/pointsreport',
    method: 'post',
    data: data
  })
}

// 修改踏查点上报
export function updatePointsreport(data) {
  return request({
    url: '/pointsreport/pointsreport',
    method: 'put',
    data: data
  })
}

// 删除踏查点上报
export function delPointsreport(id) {
  return request({
    url: '/pointsreport/pointsreport/' + id,
    method: 'delete'
  })
}
