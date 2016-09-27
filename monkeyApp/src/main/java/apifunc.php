<?php
/**
 * MAP user premission Manage API
 * URL format :  xx/apifunc.php?action=@paramact&func=@paramfunc...
 * @package Example-application
 * 
 */



// json xml plain html
function setheader($type) {
	// $type = "json"; 
	$headstr = 'Content-Type:text/'.$type.';charset=utf-8';
	// header('Content-Type:text/json;charset=utf-8');
	header($headstr);
}


function parser_action(){
	//  if(isset($_GET['action']))
	// 		$action = $_GET['action'];
	// 	else
	// 		$action = 'null';
	// 	echo '{"data":"aa"}';
	// echo "parser_action";
	// $reqmethod =  $_SERVER['REQUEST_METHOD'];

	// $result =  parser_action_to_array();
	// return $result;

// $FFabcd = '组合变量';
// $a = 'abcd';
// $c = 'FF'.$a;
// echo ${$c};
// echo count(${'_GET'});
// $action_method = '$_'.$reqmethod."['action']";
// $a ='$_GET';
//  echo ${$a}['action'];


	//@param1:client_version 	return:current_version
	$action_checkver = array("Function"=>array("name"=>"checkver","paramcount"=>1,"version"=>0.01));
	$action_login = array("Function"=>array("name"=>"login","paramcount"=>2,"version"=>0.01));
	$action_logout = array("Function"=>array("name"=>"logout","paramcount"=>1,"version"=>0.01));
	$action_getuserinfo = array("Function"=>array("name"=>"getuserinfo","paramcount"=>1,"version"=>0.01));
	$action_getlocationtab = array("Function"=>array("name"=>"getlocationtab","paramcount"=>1,"version"=>0.01));
	$action_allowlocation = array("Function"=>array("name"=>"allowlocation","paramcount"=>1,"version"=>0.01));
	


	// if($reqmethod == "POST")  {
	// 	$action_method = $_POST;
	// } else if($reqmethod == "GET" ) {
	// 	$action_method = $_GET;
	// } else {
	// 	exit("not support method");
	// }
	
	global $gaction_method;
	switch($_SERVER['REQUEST_METHOD'])
	{
		case 'GET': $gaction_method = $_GET; break;
		case 'POST': $gaction_method = $_POST; break;
		default:exit("not support method");
	}


	$action =  strtolower($gaction_method['action']);
	if(isset(${$action})) {
		// echo json_encode( ${$action});
		return  ${$action};
	} else {
		$error_action = array("Errorn"=>"1","Msg"=>"Action: (". $action . ") not found!");
		$error_resp = array("ErrorResp"=>$error_action);
		error_report($error_resp);
		exit(0);
	}
		
	// echo $action_method;
	// echo $action_method;
	// echo json_encode($b);
	// echo $_GET['action'];
	// echo json_encode($action_method);
	// echo json_encode(${'$_'.$action_method});
	
}

//function list

function checkver($action) {
	// $result = 
	// return $result;
	global $gaction_method;
	$paramcount = $action['Function']['paramcount'];
	$version = $action['Function']['version'];

	$reqver=floatval($gaction_method['version']);
	$webver=floatval($version);

	if($webver > $reqver) {
		$result = array("Result"=>array("version" =>$version,"forceupgrade"=>true,"url"=>"www.localhost.com"));
		echo json_encode($result);
	} else {
		$result = array("Result"=>array("version" =>$version,"forceupgrade"=>false,"url"=>"www.localhost.com"));
		echo json_encode($result);
	}
	
	 // echo json_encode($gaction_method);
	 // echo json_encode($gaction_method);
	// echo "1";
}









function process_data($action) {
	// echo "process_data";
	// echo json_encode( $action);

	// switch (strtolower($action['Function']['name'])) {
	// 	case 'checkver':
	// 		# code...
	// 		return checkver($action);
	// 		break;
		
		// default:
			# code...
	 // echo $action['Function']['name'];
	$realfunction = $action['Function']['name'];
	if(isset($realfunction)) {
		 echo $realfunction($action);
	} else {
		$error_action = array("Errorn"=>"2","Msg"=>"Action: (". $action . ") not process!");
		$error_resp = array("ErrorResp"=>$error_action);
		error_report($error_resp);
		exit(0);
	}

		
			// break;
	// }
	// actionlogic();
}

function output_result() {
	// echo "output_result";
	// $error_array=array("Errorn"=>"0","Msg"=>"Success");
	// error_report($error_array);
}



$result_array=array("");

function error_report($error_array) {
	echo json_encode($error_array);
}

function result_json($array) {
	echo json_encode($result_array);
}

//run

setheader("json");
$actions = parser_action();
process_data($actions);
output_result();
	








?>