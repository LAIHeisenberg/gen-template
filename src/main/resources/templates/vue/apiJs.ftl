<#escape x as x?html>
import request from '@/utils/request'

export function add(data) {
    return request({
        url: '',
        method: 'post',
        data
    })
}

export function del(ids) {
    return request({
        url: '',
        method: 'delete',
        data: ids
    })
}

export default {add,del}
</#escape>