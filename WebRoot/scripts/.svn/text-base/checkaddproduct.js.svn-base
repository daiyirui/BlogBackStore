// JavaScript Document
function checkpname()
{
  var divpname = document.getElementById("div_pname");
  if(form1.pname.value=="")
  {
    divpname.innerHTML = "<img src='/SevenUpLaptopStore/background/images/err.png'> 商品名称必须填写";
	return false;
  }
  else
  {
    divpname.innerHTML = "<img src='/SevenUpLaptopStore/background/images/ok.png'>";
	return true;
  }
}
function checkstoragetime()
{
  var divstoragetime = document.getElementById("div_storagetime");
  if(form1.storagetime.value=="")
  {
    divstoragetime.innerHTML = "<img src='/SevenUpLaptopStore/background/images/err.png'> 上架时间必须填写，格式yyyy-mm-dd";
	return false;
  }
  else
  {
    divstoragetime.innerHTML = "<img src='/SevenUpLaptopStore/background/images/ok.png'>";
	return true;
  }
}
function checkstock()
{
  var divstock = document.getElementById("div_stock");
  if(form1.stock.value=="")
  {
    divstock.innerHTML = "<img src='/SevenUpLaptopStore/background/images/err.png'> 库存量必须填写，且为整数";
	return false;
  }
  else
  {
    divstock.innerHTML = "<img src='/SevenUpLaptopStore/background/images/ok.png'>";
	return true;
  }
}
function checkprice()
{
  var divprice = document.getElementById("div_price");
  if(form1.price.value=="")
  {
    divprice.innerHTML = "<img src='/SevenUpLaptopStore/background/images/err.png'> 市场价必须填写，且为数字";
	return false;
  }
  else
  {
    divprice.innerHTML = "<img src='/SevenUpLaptopStore/background/images/ok.png'>";
	return true;
  }
}
function checkpurchaseprice()
{
  var divpurchaseprice = document.getElementById("div_purchaseprice");
  if(form1.purchaseprice.value=="")
  {
    divpurchaseprice.innerHTML = "<img src='/SevenUpLaptopStore/background/images/err.png'> 进货价必须填写，且为数字";
	return false;
  }
  else
  {
    divpurchaseprice.innerHTML = "<img src='/SevenUpLaptopStore/background/images/ok.png'>";
	return true;
  }
}
function checkshippingprice()
{
  var divshippingprice = document.getElementById("div_shippingprice");
  if(form1.shippingprice.value=="")
  {
    divshippingprice.innerHTML = "<img src='/SevenUpLaptopStore/background/images/err.png'> 出货价必须填写，且为数字";
	return false;
  }
  else
  {
    divshippingprice.innerHTML = "<img src='/SevenUpLaptopStore/background/images/ok.png'>";
	return true;
  }
}
function checkbrand()
{
  var divbrand = document.getElementById("div_brand");
  if(form1.brand.value=="")
  {
    divbrand.innerHTML = "<img src='/SevenUpLaptopStore/background/images/err.png'> 品牌必须填写";
	return false;
  }
  else
  {
    divbrand.innerHTML = "<img src='/SevenUpLaptopStore/background/images/ok.png'>";
	return true;
  }
}
function checkcpu()
{
  var divcpu = document.getElementById("div_cpu");
  if(form1.cpu.value=="")
  {
    divcpu.innerHTML = "<img src='/SevenUpLaptopStore/background/images/err.png'> CPU型号必须填写";
	return false;
  }
  else
  {
    divcpu.innerHTML = "<img src='/SevenUpLaptopStore/background/images/ok.png'>";
	return true;
  }
}
function checkharddiskcapacity()
{
  var divharddiskcapacity = document.getElementById("div_harddiskcapacity");
  if(form1.harddiskcapacity.value=="")
  {
    divharddiskcapacity.innerHTML = "<img src='/SevenUpLaptopStore/background/images/err.png'> 硬盘容量必须填写，且为整数";
	return false;
  }
  else
  {
    divharddiskcapacity.innerHTML = "<img src='/SevenUpLaptopStore/background/images/ok.png'>";
	return true;
  }
}
function checkmemorysize()
{
  var divmemorysize = document.getElementById("div_memorysize");
  if(form1.memorysize.value=="")
  {
    divmemorysize.innerHTML = "<img src='/SevenUpLaptopStore/background/images/err.png'> 内存容量必须填写，且为整数";
	return false;
  }
  else
  {
    divmemorysize.innerHTML = "<img src='/SevenUpLaptopStore/background/images/ok.png'>";
	return true;
  }
}
function checkscreensize()
{
  var divscreensize = document.getElementById("div_screensize");
  if(form1.screensize.value=="")
  {
    divscreensize.innerHTML = "<img src='/SevenUpLaptopStore/background/images/err.png'> 屏幕大小必须填写";
	return false;
  }
  else
  {
    divscreensize.innerHTML = "<img src='/SevenUpLaptopStore/background/images/ok.png'>";
	return true;
  }
}
function checkdisplaycard()
{
  var divdisplaycard = document.getElementById("div_displaycard");
  if(form1.displaycard.value=="")
  {
    divdisplaycard.innerHTML = "<img src='/SevenUpLaptopStore/background/images/err.png'> 显卡型号必须填写";
	return false;
  }
  else
  {
    divdisplaycard.innerHTML = "<img src='/SevenUpLaptopStore/background/images/ok.png'>";
	return true;
  }
}
function checkdisplaycardsize()
{
  var divdisplaycardsize = document.getElementById("div_displaycardsize");
  if(form1.displaycardsize.value=="")
  {
    divdisplaycardsize.innerHTML = "<img src='/SevenUpLaptopStore/background/images/err.png'> 显存大小必须填写，且为整数";
	return false;
  }
  else
  {
    divdisplaycardsize.innerHTML = "<img src='/SevenUpLaptopStore/background/images/ok.png'>";
	return true;
  }
}
function checkAddForm()
{
	if(!checkpname()||!checkstoragetime()||!checkstock()
		||!checkprice()||!checkpurchaseprice()
		||!checkshippingprice()||!checkbrand()
		||!checkcpu()||!checkharddiskcapacity()
		||!checkmemorysize()||!checkscreensize()
		||!checkdisplaycard()||!checkdisplaycardsize())
	{
		alert("请检查填写的信息是否全部符合要求");
		return false;
	}
	else
	{
		alert("添加成功！点击回到查看列表");
		return true;
	}
}