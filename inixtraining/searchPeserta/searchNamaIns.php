<?php 
	$nama_ins = $_GET['nama_ins'];
	
	//Importing database
	require_once('../koneksi.php');
	
	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	$sql = "SELECT K.id_kls kls,K.id_mat, K.id_ins,I.nama_ins nama_ins, M.nama_mat nama_mat, K.tgl_mulai_kls mulai, K.tgl_akhir_kls akhir
		FROM `tb_kelas` K
		JOIN tb_instruktur I
		ON K.id_ins = I.id_ins
		JOIN tb_materi M
		ON M.id_mat = K.id_mat
		WHERE I.nama_ins LIKE '%$nama_ins%'";
	
	//Mendapatkan Hasil 
	$r = mysqli_query($con,$sql);
	
	//Memasukkan Hasil Kedalam Array
	$result = array();

	while($row = mysqli_fetch_array($r)){
	array_push($result,array(
			"id_ins"=>$row['id_ins'],
			"kls"=>$row['kls'],
			"nama_ins"=>$row['nama_ins'],
			"mulai"=>$row['mulai'],
			"akhir"=>$row['akhir'],
			"nama_mat"=>$row['nama_mat']
		));
	}

	//Menampilkan dalam format JSON
	echo json_encode(array('result'=>$result));

	
	mysqli_close($con);
?>