// @ts-ignore
/* eslint-disable */
import {request} from 'umi';
import {TableListItem} from './data';

/** 获取规则列表 GET /api/rule */
export async function rule(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
  },
  options?: { [key: string]: any },
) {
  const result = await request<{
    data: TableListItem[];
    /** 列表的内容总数 */
    total?: number;
    success?: boolean;
  }>('http://127.0.0.1:8081/dependency/detect/rule/main', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
  return {
    data: result || [],
    success: true,
  };
}

export async function detect(record: TableListItem, options?: { [key: string]: any }) {
  return request<TableListItem>('http://127.0.0.1:8081/dependency/detect/', {
    data: record,
    method: 'POST',
    ...(options || {}),
  });
}

/** 新建规则 PUT /api/rule */
export async function updateRule(data: { [key: string]: any }, options?: { [key: string]: any }) {
  return request<TableListItem>('http://127.0.0.1:8081/dependency/detect/rule/update', {
    data,
    method: 'POST',
    ...(options || {}),
  });
}

/** 新建规则 POST /api/rule */
export async function addRule(data: { [key: string]: any }, options?: { [key: string]: any }) {
  return request<TableListItem>('/api/rule', {
    data,
    method: 'POST',
    ...(options || {}),
  });
}

/** 删除规则 DELETE /api/rule */
export async function removeRule(data: { key: number[] }, options?: { [key: string]: any }) {
  return request<Record<string, any>>('/api/rule', {
    data,
    method: 'DELETE',
    ...(options || {}),
  });
}
