<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>
<c:set var="ImgUrl" value="${c:encodeURL('/widgets/layout/group_box/img')}" scope="request" />
.main {
  padding: 15px;
}
.z-caption.customIcon .z-caption-content {
    width: 100%;
}
 
.z-caption.customIcon .open-false,
.z-caption.customIcon .open-true {
  background: url("${ImgUrl}/arrow.png") no-repeat right 0;
  height: 16px;  
  padding-right: 20px;
  font-weight: bold;
  float: right;
} 
.z-caption.customIcon .open-false {
  background-position: right -16px;
}
.z-groupbox-collapsed .block {
  display: block;
}
 
.z-groupbox .z-caption.folder .z-caption-content{
  background: url("${ImgUrl}/folder.png") no-repeat 0 -16px;
  height:24px;
  padding: 0 4px 0 22px;
  margin-top: 4px;
}
.z-groupbox-collapsed .z-caption.folder .z-caption-content{
  height:16px;
  background-position: 0 0;
}