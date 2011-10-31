var defaultValidateErrorPlacement = function (lable, element)
{
    if (element.hasClass("l-textarea")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
    else if (element.hasClass("l-text-field")) element.parent().ligerTip({ content: lable.html(), appendIdTo: lable });
    else lable.appendTo(element.parents("td:first").next("td"));
};

var defaultValidateSuccess = function (lable)
{
    lable.ligerHideTip();
};

var deafultValidate = function (validateElements)
{
    return  validateElements.validate({
        errorPlacement: function (lable, element)
        {
            defaultValidateErrorPlacement(lable, element);
        },
        success: function (lable)
        {
            defaultValidateSuccess(lable);
        }
    });
};
$(function ()
{
    if (jQuery.validator)
    {
        //字母数字
        jQuery.validator.addMethod("alnum", function (value, element)
        {
            return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
        }, "只能包括英文字母和数字");

        // 手机号码验证   
        jQuery.validator.addMethod("cellphone", function (value, element)
        {
            var length = value.length;
            return this.optional(element) || (length == 11 && /^(1\d{10})$/.test(value));
        }, "请正确填写手机号码");

        // 电话号码验证   
        jQuery.validator.addMethod("telephone", function (value, element)
        {
            var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
            return this.optional(element) || (tel.test(value));
        }, "请正确填写电话号码");

        // 邮政编码验证
        jQuery.validator.addMethod("zipcode", function (value, element)
        {
            var tel = /^[0-9]{6}$/;
            return this.optional(element) || (tel.test(value));
        }, "请正确填写邮政编码");

        // 汉字
        jQuery.validator.addMethod("chcharacter", function (value, element)
        {
            var tel = /^[\u4e00-\u9fa5]+$/;
            return this.optional(element) || (tel.test(value));
        }, "请输入汉字");

        // 汉字
        jQuery.validator.addMethod("qq", function (value, element)
        {
            var tel = /^[1-9][0-9]{4,}$/;
            return this.optional(element) || (tel.test(value));
        }, "请输入正确的QQ");

        // 用户名
        jQuery.validator.addMethod("username", function (value, element)
        { 
            return this.optional(element) || /^[a-zA-Z][a-zA-Z0-9_]+$/.test(value);
        }, "用户名格式不正确");
    }
});


var GetUrlParam = function(name)
{
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}
var SetButtons = function (toolbar ,url)
{
    if (!url)
    { 
        url = '../service/SystemData.ashx?Action=GetButton';
        url += '&MenuNo=' + GetUrlParam('MenuNo');
    }
    url += "&rnd" + Math.random(); 
    $.getJSON(url, function (data)
    {
        if (!data) return;
        var buttons = []; 
        $(data).each(function (i, dataitem)
        {
            var btn = { text: this.name, icon: this.icon, id: this.id };
            if (f_btnClick) btn.click = f_btnClick;
            buttons.push(btn);
        });
        toolbar.ligerToolBar({ items: buttons });
    });
};