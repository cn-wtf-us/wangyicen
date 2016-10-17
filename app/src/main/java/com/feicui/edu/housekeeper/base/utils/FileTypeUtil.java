package com.feicui.edu.housekeeper.base.utils;

import java.io.File;

/**
 * 文件类型工具类
 *
 */

public class FileTypeUtil {
	public static final String TYPE_ANY = "all";
	public static final String TYPE_TXT = "txt";
	public static final String TYPE_IMAGE = "image";
	public static final String TYPE_VIDEO = "video";
	public static final String TYPE_AUDIO = "audio";
	public static final String TYPE_ZIP = "zip";
	public static final String TYPE_APK = "apk";

	//判断当前文件属于哪种类型
	public static String getFileType(File file){
		//先获取后缀名
		String name = file.getName();
		int index = name.lastIndexOf(".");
		if (index == -1){
			return TYPE_ANY;
		}

		String dot = name.substring(index + 1, name.length());

		//循环数组，进行后缀名之间的匹配
		for (int x = 0; x < ICON_TYPE_Table.length; x++) {
			if (dot.equals(ICON_TYPE_Table[x][0])){
				return ICON_TYPE_Table[x][2];
			}
		}
		return TYPE_ANY;
	}



	private static final String[][] ICON_TYPE_Table = {
			// {文件后缀名，文件对应图像名称,　文件所属类型}
			{ "apk", "icon_video", TYPE_APK },
			{ "3gp", "icon_video", TYPE_VIDEO },
			{ "aif", "icon_audio", TYPE_AUDIO },
			{ "aifc", "icon_audio", TYPE_AUDIO },
			{ "aiff", "icon_audio", TYPE_AUDIO },
			{ "als", "icon_audio", TYPE_AUDIO },
			{ "asc", "icon_text_plain", TYPE_TXT },
			{ "asf", "icon_video", TYPE_VIDEO },
			{ "asx", "icon_video", TYPE_VIDEO },
			{ "au", "icon_audio", TYPE_AUDIO },
			{ "avi", "icon_video", TYPE_VIDEO },
			{ "awb", "icon_audio", TYPE_AUDIO },
			{ "bmp", "icon_bmp", TYPE_IMAGE },
			{ "bz2", "icon_archive", TYPE_ZIP }, { "c", "icon_c", TYPE_TXT },
			{ "cpp", "icon_cpp", TYPE_TXT }, { "css", "icon_css", TYPE_TXT },
			{ "dhtml", "icon_html", TYPE_TXT },
			{ "doc", "icon_doc", TYPE_TXT }, { "dot", "icon_doc", TYPE_TXT },
			{ "es", "icon_audio", TYPE_AUDIO },
			{ "esl", "icon_audio", TYPE_AUDIO },
			{ "fvi", "icon_video", TYPE_VIDEO },
			{ "gif", "icon_gif", TYPE_IMAGE }, { "gz", "icon_gzip", TYPE_ZIP },
			{ "h", "icon_c_h", TYPE_TXT }, { "htm", "icon_html", TYPE_TXT },
			{ "html", "icon_html", TYPE_TXT },
			{ "hts", "icon_html", TYPE_TXT },
			{ "ico", "icon_ico", TYPE_IMAGE },
			{ "ief", "icon_image", TYPE_IMAGE },
			{ "ifm", "icon_gif", TYPE_IMAGE },
			{ "ifs", "icon_image", TYPE_IMAGE },
			{ "imy", "icon_audio", TYPE_AUDIO },
			{ "it", "icon_audio", TYPE_AUDIO },
			{ "itz", "icon_audio", TYPE_AUDIO },
			{ "j2k", "icon_jpeg", TYPE_IMAGE },
			{ "java", "icon_java", TYPE_TXT },
			{ "jar", "icon_java", TYPE_ZIP },
			{ "jnlp", "icon_java", TYPE_TXT },
			{ "jpe", "icon_jpeg", TYPE_IMAGE },
			{ "jpeg", "icon_jpeg", TYPE_IMAGE },
			{ "jpg", "icon_jpeg", TYPE_IMAGE },
			{ "jpz", "icon_jpeg", TYPE_IMAGE },
			{ "js", "icon_javascript", TYPE_TXT },
			{ "lsf", "icon_video", TYPE_VIDEO },
			{ "lsx", "icon_video", TYPE_VIDEO },
			{ "m15", "icon_audio", TYPE_AUDIO },
			{ "m3u", "icon_playlist", TYPE_AUDIO },
			{ "m3url", "icon_playlist", TYPE_AUDIO },
			{ "ma1", "icon_audio", TYPE_AUDIO },
			{ "ma2", "icon_audio", TYPE_AUDIO },
			{ "ma3", "icon_audio", TYPE_AUDIO },
			{ "ma5", "icon_audio", TYPE_AUDIO },
			{ "mdz", "icon_audio", TYPE_AUDIO },
			{ "mid", "icon_audio", TYPE_AUDIO },
			{ "midi", "icon_audio", TYPE_AUDIO },
			{ "mil", "icon_image", TYPE_IMAGE },
			{ "mio", "icon_audio", TYPE_AUDIO },
			{ "mng", "icon_video", TYPE_VIDEO },
			{ "mod", "icon_audio", TYPE_AUDIO },
			{ "mov", "icon_video", TYPE_VIDEO },
			{ "movie", "icon_video", TYPE_VIDEO },
			{ "mp2", "icon_mp3", TYPE_AUDIO },
			{ "mp3", "icon_mp3", TYPE_AUDIO },
			{ "mp4", "icon_video", TYPE_VIDEO },
			{ "mpe", "icon_video", TYPE_VIDEO },
			{ "mpeg", "icon_video", TYPE_VIDEO },
			{ "mpg", "icon_video", TYPE_VIDEO },
			{ "mpg4", "icon_video", TYPE_VIDEO },
			{ "mpga", "icon_mp3", TYPE_AUDIO },
			{ "nar", "icon_zip", TYPE_ZIP },
			{ "nbmp", "icon_image", TYPE_IMAGE },
			{ "nokia-op-logo", "icon_image", TYPE_IMAGE },
			{ "nsnd", "icon_audio", TYPE_AUDIO },
			{ "pac", "icon_audio", TYPE_AUDIO },
			{ "pae", "icon_audio", TYPE_AUDIO },
			{ "pbm", "icon_bmp", TYPE_IMAGE },
			{ "pcx", "icon_image", TYPE_IMAGE },
			{ "pda", "icon_image", TYPE_IMAGE },
			{ "pdf", "icon_pdf", TYPE_TXT },
			{ "pgm", "icon_image", TYPE_IMAGE },
			{ "pict", "icon_image", TYPE_IMAGE },
			{ "png", "icon_png", TYPE_IMAGE },
			{ "pnm", "icon_image", TYPE_IMAGE },
			{ "pnz", "icon_png", TYPE_IMAGE }, { "pot", "icon_ppt", TYPE_TXT },
			{ "ppm", "icon_image", TYPE_IMAGE },
			{ "pps", "icon_ppt", TYPE_TXT }, { "ppt", "icon_ppt", TYPE_TXT },
			{ "pvx", "icon_video", TYPE_VIDEO },
			{ "qcp", "icon_audio", TYPE_AUDIO },
			{ "qt", "icon_video", TYPE_VIDEO },
			{ "qti", "icon_image", TYPE_IMAGE },
			{ "qtif", "icon_image", TYPE_IMAGE },
			{ "ra", "icon_audio", TYPE_AUDIO },
			{ "ram", "icon_audio", TYPE_AUDIO },
			{ "rar", "icon_rar", TYPE_ZIP },
			{ "ras", "icon_image", TYPE_IMAGE },
			{ "rf", "icon_image", TYPE_IMAGE },
			{ "rgb", "icon_image", TYPE_IMAGE },
			{ "rm", "icon_audio", TYPE_AUDIO },
			{ "rmf", "icon_audio", TYPE_AUDIO },
			{ "rmm", "icon_audio", TYPE_AUDIO },
			{ "rmvb", "icon_audio", TYPE_AUDIO },
			{ "rp", "icon_image", TYPE_IMAGE },
			{ "rpm", "icon_audio", TYPE_AUDIO },
			{ "rtf", "icon_text_richtext", TYPE_TXT },
			{ "rv", "icon_video", TYPE_VIDEO },
			{ "s3m", "icon_audio", TYPE_AUDIO },
			{ "s3z", "icon_audio", TYPE_AUDIO },
			{ "shtml", "icon_html", TYPE_TXT },
			{ "si6", "icon_image", TYPE_IMAGE },
			{ "si7", "icon_image", TYPE_IMAGE },
			{ "si9", "icon_image", TYPE_IMAGE },
			{ "smd", "icon_audio", TYPE_AUDIO },
			{ "smz", "icon_audio", TYPE_AUDIO },
			{ "snd", "icon_audio", TYPE_AUDIO },
			{ "stm", "icon_audio", TYPE_AUDIO },
			{ "svf", "icon_image", TYPE_IMAGE },
			{ "svg", "icon_image", TYPE_IMAGE },
			{ "svh", "icon_image", TYPE_IMAGE },
			{ "swf", "icon_flash", TYPE_VIDEO },
			{ "swfl", "icon_flash", TYPE_VIDEO },
			{ "tar", "icon_tar", TYPE_ZIP }, { "taz", "icon_tar", TYPE_ZIP },
			{ "tgz", "icon_tar", TYPE_ZIP },
			{ "tif", "icon_tiff", TYPE_IMAGE },
			{ "tiff", "icon_tiff", TYPE_IMAGE },
			{ "toy", "icon_image", TYPE_IMAGE },
			{ "tsi", "icon_audio", TYPE_AUDIO },
			{ "txt", "icon_text_plain", TYPE_TXT },
			{ "ult", "icon_audio", TYPE_AUDIO },
			{ "vdo", "icon_video", TYPE_VIDEO },
			{ "vib", "icon_audio", TYPE_AUDIO },
			{ "viv", "icon_video", TYPE_VIDEO },
			{ "vivo", "icon_video", TYPE_VIDEO },
			{ "vox", "icon_audio", TYPE_AUDIO },
			{ "vqe", "icon_audio", TYPE_AUDIO },
			{ "vqf", "icon_audio", TYPE_AUDIO },
			{ "vql", "icon_audio", TYPE_AUDIO },
			{ "wav", "icon_wav", TYPE_VIDEO },
			{ "wax", "icon_audio", TYPE_AUDIO },
			{ "wbmp", "icon_bmp", TYPE_IMAGE },
			{ "wi", "icon_image", TYPE_IMAGE },
			{ "wm", "icon_video", TYPE_VIDEO },
			{ "wma", "icon_wma", TYPE_AUDIO },
			{ "wmv", "icon_video", TYPE_VIDEO },
			{ "wmx", "icon_video", TYPE_VIDEO },
			{ "wpng", "icon_png", TYPE_IMAGE },
			{ "wv", "icon_video", TYPE_VIDEO },
			{ "wvx", "icon_video", TYPE_VIDEO },
			{ "xbm", "icon_bmp", TYPE_IMAGE },
			{ "xht", "icon_xhtml_xml", TYPE_TXT },
			{ "xhtm", "icon_xhtml_xml", TYPE_TXT },
			{ "xhtml", "icon_xhtml_xml", TYPE_TXT },
			{ "xla", "icon_xls", TYPE_TXT }, { "xlc", "icon_xls", TYPE_TXT },
			{ "xlm", "icon_xls", TYPE_TXT }, { "xls", "icon_xls", TYPE_TXT },

			{ "xlt", "icon_xls", TYPE_TXT }, { "xlw", "icon_xls", TYPE_TXT },
			{ "xm", "icon_audio", TYPE_AUDIO },
			{ "xml", "icon_xml", TYPE_TXT },
			{ "xmz", "icon_audio", TYPE_AUDIO },
			{ "xpm", "icon_image", TYPE_IMAGE },
			{ "xsit", "icon_xml", TYPE_TXT }, { "xsl", "icon_xml", TYPE_TXT },
			{ "xwd", "icon_image", TYPE_IMAGE },
			{ "zip", "icon_zip", TYPE_ZIP } };





}
