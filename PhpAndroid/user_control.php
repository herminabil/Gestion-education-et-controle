<?php

include_once 'connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function does_user_exist($email,$password)
		{
			$query = "Select categorie from compte where email='$email' and password = '$password' ";
			$result = mysqli_query($this->connection, $query);
			if(mysqli_num_rows($result)>0){
				
				$json['success'] = $email;
				//$json["categorie"] = $categorie;
				echo json_encode($json);
				mysqli_close($this -> connection);
			}else{
				
					$json['error'] = ' erreur mot de passe';
				
				echo json_encode($json);
				mysqli_close($this->connection);
			}
			
		}
		
	}
	
	
	$user = new User();
	if(isset($_POST['email'],$_POST['password'])) {
		$email = $_POST['email'];
		$password = $_POST['password'];
		
		if(!empty($email) && !empty($password)){
			
			$encrypted_password = md5($password);
			$user-> does_user_exist($email,$password);
			
		}else{
			echo json_encode("vous devez specifier le login et le mot de passe");
		}
		
	}














?>