$(function(){

    // var search = location.search;
    // var cid = search.split("=")[1];
    var cid = getParameter("cid");
    var rname = getParameter("rname");
    if (rname){
        //url解码
        rname = window.decodeURIComponent(ranme)
    }

    //页面加载完成后，调用load方法
    load(cid,null,rname);
});

function load(cid,currentPage,rname){
    $.get("route/pageQuery", {cid:cid,currentPage:currentPage,rname:rname}, function (pb) {
        //解析pageBean，展示到页面上

        //1.分页工具条数据展示
        //1.1 展示总页数和总记录数
        $("#totalPage").html(pb.totalPage);
        $("#totalCount").html(pb.totalCount);

        var lis = "";

        var firstPage = '<li onclick="javascript:load('+ cid +',1,\''+ rname +'\')"><a href="javascript:void(0);">首页</a></li>';

        //计算上一页的页码
        var beforeNum = currentPage - 1;
        if (beforeNum <= 0){
            beforeNum = 1;
        }

        var beforePage = '<li onclick="javascript:load('+ cid +', '+ beforeNum +', \''+ rname +'\')" ' +
            'class="threeword"><a href="javascript:void(0);">上一页</a></li>';

        lis += firstPage;
        lis += beforeNum;

        //1.2 展示分页页码
        /*
            1.一共展示10个页码，能够达到前5后4的效果
            2.如果前边不够5个，后边补齐10个
            3.如果后边不足4个，前边补齐10个
        */

        var begin; //开始位置
        var end; //结束位置

        //要显示10个页码
        if (pb.totalPage < 10) {
            //总页数不够10页
            begin = 1;
            end = pb.totalPage;
        } else {
            //总页码超过10页
            begin = currentPage - 5;
            end = currentPage + 4;

            if (begin < 1){
                begin = 1;
                end = begin + 9;
            }
            
            if (end > pb.totalPage){
                end = pb.totalPage;
                begin = end - 9;
            }
        }

        for (var i = begin; i < end; i++) {
            var li;
            //判断当前页码是否等于i
            if (pb.currentPage == i){
                li = '<li class="curPage" onclick="load(' + cid + ',' + i + ', \'' + rname + '\')">' +
                    '<a href="javascript:void(0);">'+ i +'</a></li>';
            } else {
                li = '<li onclick="load(' + cid + ',' + i + ', \'' + rname + '\')">' +
                    '<a href="javascript:void(0);">'+ i +'</a></li>';
            }

            lis += li;
        }

        var lastPage = '<li class="threeword"><a href="javascript:;">末页</a></li>';
        var nextPage = '<li class="threeword"><a href="javascript:;">下一页</a></li>';

        $("#pageNum").html(lis);

        //2.列表数据展示
        var route_lis = "";

        for (var i = 0; i < pb.list.length; i++) {
            var route = pb.list[i];

            var li = '<li>\n' +
                '                            <div class="img"><img src="'+ route.rimage +'" alt=""></div>\n' +
                '                            <div class="text1">\n' +
                '                                <p>'+ route.rname +'</p>\n' +
                '                                <br/>\n' +
                '                                <p>'+ route.routeIntroduce +'</p>\n' +
                '                            </div>\n' +
                '                            <div class="price">\n' +
                '                                <p class="price_num">\n' +
                '                                    <span>&yen;</span>\n' +
                '                                    <span>'+ route.price +'</span>\n' +
                '                                    <span>起</span>\n' +
                '                                </p>\n' +
                '                                <p><a href="route_detail.html?rid='+ route.rid +'">查看详情</a></p>\n' +
                '                            </div>\n' +
                '                        </li>';
            route_lis += li;
        }
        $("#route").html(route_lis);

        //定位到页面顶部
        window.scrollTo(0,0);
    })
}

