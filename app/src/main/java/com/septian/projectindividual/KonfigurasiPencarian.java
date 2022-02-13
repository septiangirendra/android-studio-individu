package com.septian.projectindividual;

public class KonfigurasiPencarian {

    public static final String URL_GET_SEARCH_PESERTA = "http://192.168.31.103/inixtraining/searchPeserta/searchNamaPst.php?nama_pst=";
    public static final String URL_GET_SEARCH_INS = "http://192.168.31.103/inixtraining/searchPeserta/searchNamaIns.php?nama_ins=";
    public static final String URL_GET_SEARCH_MAT = "http://192.168.31.103/inixtraining/searchPeserta/searchNamaMat.php?nama_mat=";
    public static final String URL_GET_SEARCH_MAT_PST = "http://192.168.31.103/inixtraining/searchPeserta/searchMateriPeserta.php?nama_pst=";

    //Instruktur
    public static final String TAG_JSON_ARRAY_INS_SEARCH = "result";
    public static final String TAG_JSON_ID_INS_SEARCH = "id_ins";
    public static final String TAG_JSON_NAMA_INS_SEARCH = "nama_ins";
    public static final String TAG_JSON_NAMA_MAT_INS_SEARCH = "nama_mat";
    public static final String TAG_JSON_MULAI_KLS_SEARCH = "mulai";
    public static final String TAG_JSON_AKHIR_KLS_SEARCH = "akhir";

    // Peserta
    public static final String TAG_JSON_ARRAY_PST_SEARCH = "result";
    public static final String TAG_JSON_ID_PST_SEARCH = "id_pst";
    public static final String TAG_JSON_NAMA_PST_SEARCH = "nama_pst";

    // Materi
    public static final String TAG_JSON_ARRAY_MAT_SEARCH = "result";
    public static final String TAG_JSON_NAMA_MAT_SEARCH = "materi";
    public static final String TAG_JSON_NAMA_PST_MAT_SEARCH = "peserta";

    //
    public static final String TAG_JSON_NAMA_PST_MAT_SEARCH_IKUT = "materi";
    public static final String TAG_JSON_ARRAY_MAT_SEARCH_IKUT = "result";

}
