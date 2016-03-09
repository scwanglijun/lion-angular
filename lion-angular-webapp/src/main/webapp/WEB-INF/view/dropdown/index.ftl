<#assign base = request.contextPath/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <title>dropdown</title>
    <link rel="stylesheet" href="${base}/resources/admin/scripts/dropdown/dropdown.css"></link>
    <script type="text/javascript" src="${base}/resources/global/plugins/dropdown/jquery.dropDown.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/scripts/dropdown/dropdown.js"></script>
</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN PAGE CONTENT INNER -->
<div class="portlet light">
    <div class="portlet-body">
        <div class="row">
            <div id="dropdown">
                <button id="dropdownMenu">
                    <span>Dropdown</span>
                    <span class="caret"></span>
                </button>
                <ul id="dropdownContent">
                    <li class="item">
                        <i class="icon-star"></i>
                        <span>icon-star</span>
                    </li>
                    <li class="item">
                        <i class="icon-home"></i>
                        <span>icon-home</span>
                    </li>
                    <li class="item">
                        <i class="icon-user"></i>
                        <span>icon-user</span>
                    </li>
                    <li class="item">
                        <i class="icon-calendar"></i>
                        <span>icon-calendar</span>
                    </li>
                    <li class="item">
                        <i class="icon-bell"></i>
                        <span>icon-bell</span>
                    </li>
                    <li class="item">
                        <i class="icon-settings"></i>
                        <span>icon-settings</span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>