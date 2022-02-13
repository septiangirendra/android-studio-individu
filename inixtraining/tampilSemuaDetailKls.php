<?php 

	//Importing database
	require_once('koneksi.php');
	
	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	$sql = "SELECT DK.id_detail_kls id_detail_kls,DK.id_kls id_kls, M.nama_mat nama_mat, COUNT(*) count
		FROM tb_detail_kelas DK
		JOIN tb_kelas K 
		ON DK.id_kls = K.id_kls 
		JOIN tb_materi M
		ON M.id_mat = K.id_mat
        GROUP BY id_kls; ";
	
	//Mendapatkan Hasil 
	$r = mysqli_query($con,$sql) or die( mysqli_error($con));
	
	
	//Memasukkan Hasil Kedalam Array
	$result = array();

	while($row = mysqli_fetch_array($r)){
	array_push($result,array(
			"id_detail_kls"=>$row['id_detail_kls'],
			"id_kls"=>$row['id_kls'],
			"nama_mat"=>$row['nama_mat'],
			"count"=>$row['count']
		));
	}

	//Menampilkan dalam format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>