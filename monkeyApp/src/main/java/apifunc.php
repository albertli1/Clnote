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
	$headstr1 = 'Content-Type:text/'.$type.';charset=utf-8';
	// header('Content-Type:text/json;charset=utf-8');
	header($headstr1);
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
	$action_checkver = array("Function"=>array("name"=>"checkver","paramcount"=>"1","version"=>"0.1"));
	$action_login = array("Function"=>array("name"=>"checkver","paramcount"=>"1","version"=>"0.1"));
	$action_logout = array("Function"=>array("name"=>"checkver","paramcount"=>"1","version"=>"0.1"));
	$action_allowlocation = array("Function"=>array("name"=>"checkver","paramcount"=>"1","version"=>"0.1"));
	


	// if($reqmethod == "POST")  {
	// 	$action_method = $_POST;
	// } else if($reqmethod == "GET" ) {
	// 	$action_method = $_GET;
	// } else {
	// 	exit("not support method");
	// }

	switch($_SERVER['REQUEST_METHOD'])
	{
		case 'GET': $action_method = $_GET; break;
		case 'POST': $action_method = $_POST; break;
		default:exit("not support method");
	}

	$action =  $action_method['action'];
	if(isset(${$action}))
		echo json_encode( ${$action});
	else {
		$error_action = array("Errorn"=>"1","Msg"=>"Action: (". $action . ") not found!");
		$error_resp = array("ErrorResp"=>$error_action);
		error_report($error_resp);
	}
		
	// echo $action_method;
	// echo $action_method;
	// echo json_encode($b);
	// echo $_GET['action'];
	// echo json_encode($action_method);
	// echo json_encode(${'$_'.$action_method});
	
}


function process_data() {
	// echo "process_data";
	
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
parser_action();
process_data();
output_result();
	








?>