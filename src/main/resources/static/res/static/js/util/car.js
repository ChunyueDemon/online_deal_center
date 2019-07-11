/*
*@Name: 母婴商城
*@Author: xuzhiwen
*@Copyright:layui.com
*/

layui.define(['layer'],function(exports){
	var layer = layui.layer;
	
var car = {
  init : function(){
      var uls = document.getElementById('list-cont').getElementsByTagName('ul');//每一行
      var checkInputs = document.getElementsByClassName('check'); // 所有勾选框
      var checkAll = document.getElementsByClassName('check-all'); //全选框
      var SelectedPieces = document.getElementsByClassName('Selected-pieces')[0];//总件数
      var piecesTotal = document.getElementsByClassName('pieces-total')[0];//总价
      var batchdeletion = document.getElementsByClassName('batch-deletion')[0]//批量删除按钮
      //计算
      function getTotal(){
          var seleted = 0,price = 0;
          for(var i = 0; i < uls.length;i++){
        			if(uls[i].getElementsByTagName('input')[0].checked){
                seleted += parseInt(uls[i].getElementsByClassName('Quantity-input')[0].value);
                price += parseFloat(uls[i].getElementsByClassName('sum')[0].innerHTML);
              }
      		}
          SelectedPieces.innerHTML = seleted;
          piecesTotal.innerHTML = '￥' + price.toFixed(2);
      }

      // 小计
      function getSubTotal(ul){
        var unitprice = parseFloat(ul.getElementsByClassName('th-su')[0].innerHTML);
        var count = parseInt(ul.getElementsByClassName('Quantity-input')[0].value);
        var SubTotal = parseFloat(unitprice*count);
        ul.getElementsByClassName('sum')[0].innerHTML = SubTotal.toFixed(2);
      }

      for(var i = 0; i < uls.length;i++){
          getSubTotal(uls[i]);
      };
      var seleted = 0,price = 0;
      for(var i = 0; i < uls.length;i++){
          if(uls[i].getElementsByTagName('input')[0].checked){
              seleted += parseInt(uls[i].getElementsByClassName('Quantity-input')[0].value);
              price += parseFloat(uls[i].getElementsByClassName('sum')[0].innerHTML);
          }
      }
      document.getElementsByClassName('Selected-pieces')[0].innerHTML = seleted;
      document.getElementsByClassName('pieces-total')[0].innerHTML = '￥' + price.toFixed(2);

      for(var i = 0;i < checkInputs.length;i++){
        checkInputs[i].onclick = function(){
          if(this.className === 'check-all check'){
            for(var j = 0;j < checkInputs.length; j++){
              checkInputs[j].checked = this.checked;
            }
          }
          if(this.checked == false){
            for(var k = 0;k < checkAll.length;k++){
              checkAll[k].checked = false;
            }
          }
          getTotal();
        }
      }

      for(var i = 0; i < uls.length;i++){
        uls[i].onclick = function(e){
          e = e || window.event;
          var el = e.srcElement;
          var cls = el.className;
          var input = this.getElementsByClassName('Quantity-input')[0];
          var less = this.getElementsByClassName('less')[0];
          var val = parseInt(input.value);
          var that = this;
          switch(cls){
            case 'add layui-btn':
              input.value = val + 1;
              getSubTotal(this);
              break;
            case 'less layui-btn':
              if(val > 1){
                input.value = val - 1;
              }
              getSubTotal(this);
              break;
            case 'dele-btn':
              layer.confirm('你确定要删除吗',{
                yes:function(index,layero){
                    $.post(
                        "deleteCartItem",
                        {item_id:that.getElementsByClassName('item_id')[0].value},
                        function (response) {
                            if (response){
                                layer.close(index);
                                that.parentNode.removeChild(that);
                                layer.msg("从购物车删除成功！", {icon: 6});
                            }

                            else
                                layer.msg("从购物车删除失败！", {icon: 5});
                        }
                    )

                }
              })
              break;
          }
          getTotal()
        }
      }
      batchdeletion.onclick = function(){
        if(SelectedPieces.innerHTML != 0){
          layer.confirm('你确定要删除吗',{
            yes:function(index,layero){
              layer.close(index)
              for(var i = 0;i < uls.length;i++){
                  var uls111=uls[i];
                 var input = uls[i].getElementsByTagName('input')[0];
                if(input.checked){
                    $.post(
                        "deleteCartItem",
                        {item_id:uls[i].getElementsByClassName('item_id')[0].value},
                        function (response) {
                            if (response){
                                uls111.parentNode.removeChild(uls111);
                                i--;
                                layer.msg("从购物车删除成功！", {icon: 6});
                            }
                            else
                                layer.msg("从购物车删除失败！", {icon: 5});
                        }
                    );

                }
              }
              getTotal()
            }

          })
        }else{
          layer.msg('请选择商品')
        }

      };

        checkAll[0].checked = true;
        checkAll[0].onclick();
  	  }  	

  }
  exports('car',car)
}); 