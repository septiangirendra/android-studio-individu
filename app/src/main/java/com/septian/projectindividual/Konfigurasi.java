package com.septian.projectindividual;

public class Konfigurasi {

    // Instruktur
    public static final String URL_GET_ALL = "http://192.168.31.103/inixtraining/tampilSemuaIns.php";
    public static final String URL_GET_DETAIL = "http://192.168.31.103/inixtraining/tampilDetailIns.php?id=";
    public static final String URL_GET_DELETE = "http://192.168.31.103/inixtraining/hapusIns.php?id=";
    public static final String URL_GET_UPDATE = "http://192.168.31.103/inixtraining/updateInstruktur.php?id=";
    public static final String URL_GET_ADD = "http://192.168.31.103/inixtraining/tambahIns.php";


    // Materi
    public static final String URL_GET_ALL_MAT = "http://192.168.31.103/inixtraining/tampilSemuaMat.php";
    public static final String URL_GET_DETAIL_MAT = "http://192.168.31.103/inixtraining/tampilDetailMat.php?id=";
    public static final String URL_GET_UPDATE_MAT = "http://192.168.31.103/inixtraining/updateMateri.php?id=";
    public static final String URL_GET_DELETE_MAT = "http://192.168.31.103/inixtraining/hapusMateri.php?id=";
    public static final String URL_GET_ADD_MAT = "http://192.168.31.103/inixtraining/tambahMat.php";

    // Peserta
    public  static final String URL_GET_ALL_PST = "http://192.168.31.103/inixtraining/tampilSemuaPst.php";
    public  static final String URL_GET_DETAIL_PST = "http://192.168.31.103/inixtraining/tampilDetailPst.php?id=";
    public  static final String URL_GET_UPDATE_PST = "http://192.168.31.103/inixtraining/updatePst.php?id=";
    public  static final String URL_GET_DELETE_PST = "http://192.168.31.103/inixtraining/hapusPst.php?id=";
    public  static final String URL_GET_ADD_PST = "http://192.168.31.103/inixtraining/tambahPeserta.php";


    // Detail Kelas
    public  static final String URL_GET_ALL_DTL_KLS = "http://192.168.31.103/inixtraining/tampilSemuaDetailKls.php";
    public  static final String URL_GET_ADD_DTL_KLS = "http://192.168.31.103/inixtraining/tambahDetailKls.php";
    public  static final String URL_GET_DETAIL_DTL_KLS = "http://192.168.31.103/inixtraining/tampilDetailDetailKls.php?id=";

    // key and value json yang muncul di browser
    public static final String KEY_DTL_KLS_ID = "id_detail_kls";
    public static final String KEY_DTL_KLS_ID_KLS = "id_kls";
    public static final String KEY_DTL_KLS_ID_PST = "id_pst";
    // Untuk menampilkan data Detail Kelas
    public static final String TAG_JSON_ARRAY_DTL_KLS = "result";
    public static final String TAG_JSON_DTL_KLS = "id_detail_kls";
    public static final String TAG_JSON_KLS_DTL_KLS = "id_kls";
    public static final String TAG_JSON_MAT_DTL_KLS = "nama_mat";
    public static final String TAG_JSON_INS_DTL_KLS = "nama_ins";
    public static final String TAG_JSON_COUNT_DTL_KLS = "count";
    public static final String TAG_JSON_PST_DTL_KLS = "nama_pst";


    //Kelas
    public  static final String URL_GET_ALL_KLS = "http://192.168.31.103/inixtraining/tampilSemuaKls.php";
    public  static final String URL_GET_DETAIL_KLS = "http://192.168.31.103/inixtraining/tampilDetailKls.php?id=";
    public  static final String URL_GET_DELETE_KLS = "http://192.168.31.103/inixtraining/hapusKls.php?id=";
    public  static final String URL_GET_ADD_KLS = "http://192.168.31.103/inixtraining/tambahKls.php";
    // Untuk menampilkan data Kelas
    public static final String TAG_JSON_ARRAY_KLS = "result";
    public static final String TAG_JSON_ID_KLS = "id_kls";
    public static final String TAG_JSON_TGL_MULAI = "tgl_mulai_kls";
    public static final String TAG_JSON_TGL_AKHIR = "tgl_akhir_kls";
    public static final String TAG_JSON_NAMA_INS_KLS = "nama_ins";
    public static final String TAG_JSON_NAMA_MAT_KLS = "nama_mat";
    // key add nilai JSON yang di web API Kelas
    public static final String KEY_KLS_ID ="id_kls";
    public static final String KEY_KLS_MULAI ="tgl_mulai";
    public static final String KEY_KLS_SELESAI ="tgl_selesai";
    public static final String KEY_ID_INS_KLS ="id_ins";
    public static final String KEY_ID_MAT_KLS ="id_mat";



    // key add nilai JSON yang di web API Peserta
    public static final String KEY_PST_ID ="id_pst";
    public static final String KEY_PST_NAMA ="nama_pst";
    public static final String KEY_PST_EMAIL ="email_pst";
    public static final String KEY_PST_KONTAK ="hp_pst";

    // Untuk menampilkan data Peserta
    public static final String TAG_JSON_ARRAY_PST = "result";
    public static final String TAG_JSON_ID_PST = "id_pst";
    public static final String TAG_JSON_NAMA_PST = "nama_pst";
    public static final String TAG_JSON_EMAIL_PST = "email_pst";
    public static final String TAG_JSON_HP_PST = "hp_pst";

    // key add nilai JSON yang di web API instruktur
    public static final String KEY_INS_ID ="id_ins";
    public static final String KEY_INS_NAMA ="nama_ins";
    public static final String KEY_INS_EMAIL ="email_ins";
    public static final String KEY_INS_KONTAK ="hp_ins";

    // Untuk menampilkan data Instruktur
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_JSON_ID_INS = "id_ins";
    public static final String TAG_JSON_NAMA_INS = "nama_ins";
    public static final String TAG_JSON_EMAIL_INS = "email_ins";
    public static final String TAG_JSON_HP_INS = "hp_ins";


    // key add nilai JSON yang di web API materi
    public static final String KEY_MAT_ID = "id_mat";
    public static final String KEY_NAMA_MAT = "nama_mat";

    // Untuk menampilkan data Materi
    public static final String TAG_JSON_ARRAY_MAT = "result";
    public static final String TAG_JSON_ID_MAT = "id_mat";
    public static final String TAG_JSON_NAMA_MAT = "nama_mat";



    // variabel untuk mebawa id
    public static final String INS_ID = "id_ins";
    public static final String MAT_ID = "id_mat";
    public static final String PST_ID = "id_pst";
    public static final String KLS_ID = "id_kls";
    public static final String DTL_KLS_ID = "id_kls";

}
