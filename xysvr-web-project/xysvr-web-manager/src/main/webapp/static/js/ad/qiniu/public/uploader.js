/*global Qiniu */
/*global plupload */
/*global FileProgress */
/*global hljs */

$(function() {

    /* 广告小图配置 */
    var uploaderThumb = Qiniu.uploader({
        runtimes : 'html5,flash,html4',
        browse_button : 'pickfilesThumb',
        container : 'containerThumb',
        drop_element : 'containerThumb',
        max_file_size : '1mb',
        flash_swf_url : 'js/plupload/Moxie.swf',
        dragdrop : true,
        chunk_size : '4mb',
        uptoken_url : $('#uptoken_url').val(),
        domain : QINIU_PUBLIC_BUCKET_DOMAIN,
        // downtoken_url : $('#downtoken_url').val(),
        filters : {
            mime_types : [ // 只允许选择上传图片
            {
                title : "Image files",
                extensions : "jpg,gif,png"
            } ],
            prevent_duplicates : true
        },
        // unique_names: true,
        // save_key: true,
        // x_vars: {
        // 'id': '1234',
        // 'time': function(up, file) {
        // var time = (new Date()).getTime();
        // // do something with 'time'
        // return time;
        // },
        // },
        auto_start : true,
        init : {
            'FilesAdded' : function(up, files) {
                $('table').show();
                $('#successThumb').hide();
                plupload.each(files, function(file) {
                    var progress = new FileProgress(file, 'fsUploadProgressThumb');
                    progress.setStatus("等待...");
                });
            },
            'BeforeUpload' : function(up, file) {
                var progress = new FileProgress(file, 'fsUploadProgressThumb');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                if (up.runtime === 'html5' && chunk_size) {
                    progress.setChunkProgess(chunk_size);
                }
            },
            'UploadProgress' : function(up, file) {
                var progress = new FileProgress(file, 'fsUploadProgressThumb');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                progress.setProgress(file.percent + "%", up.total.bytesPerSec, chunk_size);
            },
            'UploadComplete' : function() {
                $('#successThumb').show();
            },
            'FileUploaded' : function(up, file, info) {
                var domain = up.getOption('domain');
                var res = $.parseJSON(info);

                // 组装json
                // var thumbJson = '{"path":"' + res.key + '","size":' + res.size + ',"mime":"' + res.mime + '","hash":"' + res.hash + '","width":' + res.width + ',"height":' + res.height +
                // ',"type":"THUM_IMG"' + '}';

                var thumbJsonData = {
                    path : res.path,
                    size : res.size,
                    mime : res.mime,
                    hash : res.hash,
                    width : res.width,
                    height : res.height,
                    type : "THUM_IMG"
                };

                var thumbJson = JSON.stringify(thumbJsonData);

                // console.info(thumbJson); // 打印调试用

                $('#thumbJson').val(thumbJson);

                var progress = new FileProgress(file, 'fsUploadProgressThumb');
                progress.setComplete(up, info);
            },
            'Error' : function(up, err, errTip) {
                $('table').show();
                var progress = new FileProgress(err.file, 'fsUploadProgressThumb');
                progress.setError();
                progress.setStatus(errTip);
            },
            'Key' : function(up, file) {
                var key = "ad/" + file.name;
                // do something with key
                return key
            }
        }
    });

    /* 广告大图配置 */
    var uploaderDesc = Qiniu.uploader({
        runtimes : 'html5,flash,html4',
        browse_button : 'pickfilesDesc',
        container : 'containerDesc',
        drop_element : 'containerDesc',
        max_file_size : '1mb',
        flash_swf_url : 'js/plupload/Moxie.swf',
        dragdrop : true,
        chunk_size : '4mb',
        uptoken_url : $('#uptoken_url').val(),
        domain : QINIU_PUBLIC_BUCKET_DOMAIN,
        // downtoken_url : $('#downtoken_url').val(),
        filters : {
            mime_types : [ // 只允许选择上传图片
            {
                title : "Image files",
                extensions : "jpg,gif,png"
            } ],
            prevent_duplicates : true
        },
        // unique_names: true,
        // save_key: true,
        // x_vars: {
        // 'id': '1234',
        // 'time': function(up, file) {
        // var time = (new Date()).getTime();
        // // do something with 'time'
        // return time;
        // },
        // },
        auto_start : true,
        init : {
            'FilesAdded' : function(up, files) {
                $('table').show();
                $('#successDesc').hide();
                plupload.each(files, function(file) {
                    var progress = new FileProgress(file, 'fsUploadProgressDesc');
                    progress.setStatus("等待...");
                });
            },
            'BeforeUpload' : function(up, file) {
                var progress = new FileProgress(file, 'fsUploadProgressDesc');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                if (up.runtime === 'html5' && chunk_size) {
                    progress.setChunkProgess(chunk_size);
                }
            },
            'UploadProgress' : function(up, file) {
                var progress = new FileProgress(file, 'fsUploadProgressDesc');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                progress.setProgress(file.percent + "%", up.total.bytesPerSec, chunk_size);

            },
            'UploadComplete' : function() {
                $('#successDesc').show();
            },
            'FileUploaded' : function(up, file, info) {
                var domain = up.getOption('domain');
                var res = $.parseJSON(info);

                // 组装json
                // var descJson = '{"path":"' + res.key + '","size":' + res.size + ',"mime":"' + res.mime + '","hash":"' + res.hash + '","width":' + res.width + ',"height":' + res.height +
                // ',"type":"DESC_IMG"' + '}';

                var descJsonData = {
                    path : res.path,
                    size : res.size,
                    mime : res.mime,
                    hash : res.hash,
                    width : res.width,
                    height : res.height,
                    type : "DESC_IMG"
                };

                var descJson = JSON.stringify(descJsonData);

                // console.info(descJson);// 打印调试用

                $('#descJson').val(descJson);

                var progress = new FileProgress(file, 'fsUploadProgressDesc');
                progress.setComplete(up, info);
            },
            'Error' : function(up, err, errTip) {
                $('table').show();
                var progress = new FileProgress(err.file, 'fsUploadProgressDesc');
                progress.setError();
                progress.setStatus(errTip);
            },
            'Key' : function(up, file) {
                var key = "ad/" + file.name;
                // do something with key
                return key
            }
        }
    });

    $('#containerDesc').on('dragenter', function(e) {
        e.preventDefault();
        $('#containerThumb').addClass('draging');
        e.stopPropagation();
    }).on('drop', function(e) {
        e.preventDefault();
        $('#containerDesc').removeClass('draging');
        e.stopPropagation();
    }).on('dragleave', function(e) {
        e.preventDefault();
        $('#containerDesc').removeClass('draging');
        e.stopPropagation();
    }).on('dragover', function(e) {
        e.preventDefault();
        $('#containerDesc').addClass('draging');
        e.stopPropagation();
    });

    uploaderThumb.bind('FileUploaded', function() {
        console.log('hello man,thumbPic file is uploaded');
    });

    uploaderDesc.bind('FileUploaded', function() {
        console.log('hello man,descPic file is uploaded');
    });

    $('body').on('click', 'table button.btn', function() {
        $(this).parents('tr').next().toggle();
    });

    var getRotate = function(url) {
        if (!url) {
            return 0;
        }
        var arr = url.split('/');
        for (var i = 0, len = arr.length; i < len; i++) {
            if (arr[i] === 'rotate') {
                return parseInt(arr[i + 1], 10);
            }
        }
        return 0;
    };

    $('#myModal-img .modal-body-footer').find('a').on('click', function() {
        var img = $('#myModal-img').find('.modal-body img');
        var key = img.data('key');
        var oldUrl = img.attr('src');
        var originHeight = parseInt(img.data('h'), 10);
        var fopArr = [];
        var rotate = getRotate(oldUrl);
        if (!$(this).hasClass('no-disable-click')) {
            $(this).addClass('disabled').siblings().removeClass('disabled');
            if ($(this).data('imagemogr') !== 'no-rotate') {
                fopArr.push({
                    'fop' : 'imageMogr2',
                    'auto-orient' : true,
                    'strip' : true,
                    'rotate' : rotate,
                    'format' : 'png'
                });
            }
        }
        else {
            $(this).siblings().removeClass('disabled');
            var imageMogr = $(this).data('imagemogr');
            if (imageMogr === 'left') {
                rotate = rotate - 90 < 0 ? rotate + 270 : rotate - 90;
            }
            else if (imageMogr === 'right') {
                rotate = rotate + 90 > 360 ? rotate - 270 : rotate + 90;
            }
            fopArr.push({
                'fop' : 'imageMogr2',
                'auto-orient' : true,
                'strip' : true,
                'rotate' : rotate,
                'format' : 'png'
            });
        }

        $('#myModal-img .modal-body-footer').find('a.disabled').each(function() {

            var watermark = $(this).data('watermark');
            var imageView = $(this).data('imageview');
            var imageMogr = $(this).data('imagemogr');

            if (watermark) {
                fopArr.push({
                    fop : 'watermark',
                    mode : 1,
                    image : 'http://www.b1.qiniudn.com/images/logo-2.png',
                    dissolve : 100,
                    gravity : watermark,
                    dx : 100,
                    dy : 100
                });
            }

            if (imageView) {
                var height;
                switch (imageView) {
                case 'large':
                    height = originHeight;
                    break;
                case 'middle':
                    height = originHeight * 0.5;
                    break;
                case 'small':
                    height = originHeight * 0.1;
                    break;
                default:
                    height = originHeight;
                    break;
                }
                fopArr.push({
                    fop : 'imageView2',
                    mode : 3,
                    h : parseInt(height, 10),
                    q : 100,
                    format : 'png'
                });
            }

            if (imageMogr === 'no-rotate') {
                fopArr.push({
                    'fop' : 'imageMogr2',
                    'auto-orient' : true,
                    'strip' : true,
                    'rotate' : 0,
                    'format' : 'png'
                });
            }
        });

        var newUrl = Qiniu.pipeline(fopArr, key);

        var newImg = new Image();
        img.attr('src', 'loading.gif');
        newImg.onload = function() {
            img.attr('src', newUrl);
            img.parent('a').attr('href', newUrl);
        };
        newImg.src = newUrl;
        return false;
    });

});
