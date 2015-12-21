$(document).ready(function () {
    
	loadDCList();
    fillBlank();

    function loadDCList() {
        $.ajax({
            type: 'get',
            async: false,
            url: 'ClusterAction/DatacenterList',
            dataType: 'json',
            success: function (array) {
                if (array.length >= 1) {
                    $("#dcselect").html("");
                    $.each(array, function (index, item) {
                        $("#dcselect").append("<option value='" + item.dcid + "'>" + decodeURIComponent(item.dcname) + "</option>");
                    });
                }
                else {
                    $('#dcselect').html('<option>请先添加数据中心</option>');
                    $('#dcselect').attr('disabled', true).addClass('oc-disable');
                    $('#createClusterAction').attr('disabled', true);
                }
            },
            error: function () {
                $('#dcselect').html('<option>请先添加数据中心</option>');
                $('#dcselect').attr('disabled', true).addClass('oc-disable');
                $('#createClusterAction').attr('disabled', true);
            }
        });
    }

    function fillBlank() {
        var type = $('#ClusterModalContainer').attr('type');
        if ('edit' == type) {
            $('#modaltitle').html('修改集群<a class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove"></span></a>');
            var poolid = '';
            var poolname = '';
            var pooldesc = '';
            var dcuuid = '';

            $('input[name="poolrow"]:checked').each(function () {
                poolid = $(this).parent().parent().attr("poolid");
                poolname = $(this).parent().parent().attr("poolname");
                pooldesc = $(this).parent().parent().attr("pooldesc");
                dcuuid = $(this).parent().parent().attr("dcid");
            });
            $('#modalcontent').attr('poolid', poolid);

            $('#pool_name').val(poolname);
            if ('' != dcuuid) {
                $('#dcselect').val(dcuuid);
            }
            $('#pool_desc').val(pooldesc);
        }
    }


    $('#createClusterAction').on('click', function (event) {
        event.preventDefault();
        var valid = $("#create-form").valid();
        var errorLen = $('#dcselect').parent().find('.oc-error').length;
        if (valid && errorLen == 0) {
            var poolName = document.getElementById("pool_name").value;
            var poolType = $('#pool_type').val();
            var poolDesc = document.getElementById("pool_desc").value;
            var dcuuid = $("#dcselect").val();
            var dcname = $("#dcselect").find("option:selected").text();
            var type = $('#ClusterModalContainer').attr('type');
            if ('new' == type) {
                $.ajax({
                    type: 'post',
                    url: 'ClusterAction/Create',
                    data: {poolname: poolName, pooltype: poolType, pooldesc: poolDesc, dcuuid: dcuuid, dcname: dcname},
                    dataType: 'json',
                    success: function (array) {
                        if (array.length == 1) {
                            var obj = array[0];
                            var poolname = decodeURIComponent(obj.poolname);
                            var pooldesc = decodeURIComponent(obj.pooldesc);
                            var poolid = obj.poolid;
                            var poolmaster = obj.poolmaster;
                            var createdate = obj.createdate;
                            var dcuuid = obj.dcuuid;
                            var dcname = decodeURIComponent(obj.dcname);
                            var showid = "pool-" + poolid.substring(0, 8);
                            var mytr = '<tr poolid="' + poolid + '" poolname="' + poolname + '" pooldesc="' + pooldesc + '" dcid="' + dcuuid + '"><td class="rcheck"><input type="checkbox" name="poolrow"></td>'
                                + '<td><a class="id">' + showid + '</a></td><td>' + poolname + '</td><td>' + poolmaster + '</td><td class="pod" state="loaded"><a>' + dcname + '</a></td><td>0</td><td>0</td><td class="time">' + createdate + '</td></tr>';
                            $("#tablebody").prepend(mytr);
                        }

                    },
                    error: function () {

                    }
                });
            } else if ('edit' == type) {
                var poolid = $('#modalcontent').attr('poolid');
                $.ajax({
                    type: 'post',
                    url: 'ClusterAction/Update',
                    data: {pooluuid: poolid, poolname: poolName, pooltype: poolType, pooldesc: poolDesc, dcuuid: dcuuid},
                    dataType: 'text',
                    success: function () {
                        var thistr = $("#tablebody").find('[poolid="' + poolid + '"]');
                        thistr.attr('poolname', poolName);
                        thistr.attr('pooldesc', poolDesc);
                        thistr.attr('dcid', dcuuid);
                        thistr.children('td').eq(2).html(poolName);
                        thistr.children('td').eq(4).html('<a>' + dcname + '</a>');
                    },
                    error: function () {
                    }
                });
            }
            $('#ClusterModalContainer').modal('hide');
        }
    });

    $("#create-form").validate({
        rules: {
            pool_name: {
                required: true,
                maxlength: 20,
                legal: true
            },
            pool_desc: {
                required: true,
                maxlength: 80,
                legal: true
            }
        },
        messages: {
            pool_name: {
                required: "<span class='help'>集群名称不能为空</span>",
                maxlength: "<span class='help'>集群名称不能超过20个字符</span>",
                legal: "<span class='help'>集群名称包含非法字符</span>"
            },
            pool_desc: {
                required: "<span class='help'>集群描述不能为空</span>",
                maxlength: "<span class='help'>集群描述不能超过80个字符</span>",
                legal: "<span class='help'>集群描述包含非法字符</span>"
            }
        }
    });
});