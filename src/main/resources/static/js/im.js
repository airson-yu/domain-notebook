new Vue({
    el: '#app',
    data() {
        return {visible: false, im_list: [], uid: null, text_content: ''}
    },
    methods: {
        send_im_msg() {
            let that = this;
            that.append_im_msg();
        },
        append_im_msg() {
            let that = this;
            let content = that.text_content;
            let data = {
                'content': content,
                'ts': new Date().getTime(),
                'state': 1 //1:local_append, 11:sending_to_server 21:sent_to_server_success 21:sent_to_server_failure
            }
            let ary_len = that.im_list.push(data);
            that.remote_send_msg(data, ary_len);
        },
        clear() {
            let that = this;
            that.im_list = [];
        },
        remote_send_msg(data, ary_len) {
            let that = this;
            let index = ary_len - 1;
            that.im_list[index].state = 11;
            //let param = JSON.stringify(data);
            // https://www.kancloud.cn/yunye/axios/234845
            // https://blog.csdn.net/LostSh/article/details/68923874
            // https://www.cnblogs.com/small-coder/p/9115972.html
            // 当Ajax以application/x-www-form-urlencoded格式上传即使用JSON对象，后台需要使用@RequestParam 或者Servlet获取。 当Ajax以application/json格式上传即使用JSON字符串，后台需要使用@RquestBody获取。
            let url = '/v1/im/json/send';
            let param = data;
            //axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
            axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
            axios.post(url, param).then(function (response) {
                if (response && response.success) {
                    that.im_list[index].state = 20;
                } else {
                    that.im_list[index].state = 21;
                }
            }).catch(function (err) {
                console.log(err)
            });
        }

    },
})