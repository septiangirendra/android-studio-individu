<?php 
	$nama_pst = $_GET['nama_pst'];
	
	//Importing database
	require_once('../koneksi.php');
	
	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	$sql = "SELECT `id_pst`, `nama_pst`, `email_pst`, `hp_pst` FROM `tb_peserta` WHERE nama_pst LIKE '%$nama_pst%'";
	
	//Mendapatkan Hasil 
	$r = mysqli_query($con,$sql);
	
	//Memasukkan Hasil Kedalam Array
	$result = array();

	while($row = mysqli_fetch_array($r)){
	array_push($result,array(
			"id_pst"=>$row['id_pst'],
			"nama_pst"=>$row['nama_pst'],
			"email_pst"=>$row['email_pst'],
			"hp_pst"=>$row['hp_pst']
		));
	}

	//Menampilkan dalam format JSON
	echo json_encode(array('result'=>$result));

	
	mysqli_close($con);
?>