<?php 
	$nama_pst = $_GET['nama_pst'];
	
	//Importing database
	require_once('../koneksi.php');
	
	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	$sql = "SELECT m.nama_mat nama_mat, P.nama_pst peserta
		FROM `tb_detail_kelas` DK
		JOIN tb_peserta P 
		ON DK.id_pst = P.id_pst
		JOIN tb_kelas K
		ON DK.id_kls = K.id_kls
		JOIN tb_materi M 
		ON M.id_mat = K.id_mat
		WHERE P.nama_pst LIKE '%$nama_pst%'";
	
	//Mendapatkan Hasil 
	$r = mysqli_query($con,$sql)or die( mysqli_error($con));
	
	//Memasukkan Hasil Kedalam Array
	$result = array();

	while($row = mysqli_fetch_array($r)){
	array_push($result,array(
			"materi"=>$row['nama_mat'],
			"peserta"=>$row['peserta']
		));
	}

	//Menampilkan dalam format JSON
	echo json_encode(array('result'=>$result));

	
	mysqli_close($con);
?>