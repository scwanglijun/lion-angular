;(function($){
   $.Dropdown = function(obj,opt){
       this.oBox = null;
	   this.oBtnMenu = null;
	   this.oUl = null;
	   this.aItem = null;
	   this.liSpan = null;
	   this.oLi = null;
	   this.innerHTML = null;
	   var This = this;
	   this.settings = {  //默认参数
		  'menuWidth' : 120,
		  'menuHeight' : 30,
		  'itemHeight' : 40,
		  'dropDownLeft' : 30,
		  'dropDownTop' : 30
	   }

       //DOM界面搭建
	   $.extend(this.settings,opt);
		this.oBox = $('#'+obj);
		
		this.innerHTML = $('<button id="dropdownMenu">'+
	         '<span>Dropdown</span>'+
	         '<span class="caret"></span>'+
	      '</button>'+
	      '<ul id="dropdownContent"></ul>');
		this.oBox.append(this.innerHTML);
		
		this.oBtnMenu = $('#dropdownMenu');
		this.oUl = $('#dropdownContent');
		
		for(var i=0;i<this.settings.arrList.length;i++){
		  this.oLi = $('<li class="item">'+
				 '<img src="'+ this.settings.arrList[i].imgUrl +'" width="32px" height="32px"/>'+
				 '<span>'+ this.settings.arrList[i].spanTxt +'</span>'+
			  '</li>');
		  this.oUl.append(this.oLi);
		}
		
		this.aItem = this.oUl.find('.item');
		this.liSpan = this.aItem.find('span');
		
		this.oBox.css({
		   'left': this.settings.dropDownLeft,
		   'top': this.settings.dropDownTop
		});
		this.oBox.css({
		   'width': this.settings.menuWidth			  
		});
		this.oBtnMenu.css({
		   'height': this.settings.menuHeight
		});
		this.aItem.css({
		   'height': this.settings.itemHeight
		});
		

        //下拉列表点击效果实现
	    this.oBox.delegate(this.oBtnMenu,'click',function(){
		   This.oUl.slideToggle('200');					 
		});	
		
		this.aItem.on('click',function(){
									   
	       This.oBtnMenu.find('span').eq(0).html($(this).find('span').html());							
		
		});
   }
})(jQuery);  
