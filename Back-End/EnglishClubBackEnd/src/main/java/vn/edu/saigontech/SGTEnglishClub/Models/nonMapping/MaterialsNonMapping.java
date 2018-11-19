package vn.edu.saigontech.SGTEnglishClub.Models.nonMapping;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import vn.edu.saigontech.SGTEnglishClub.Models.Admin;
import vn.edu.saigontech.SGTEnglishClub.Models.File;
import vn.edu.saigontech.SGTEnglishClub.Models.Materialtype;

public class MaterialsNonMapping {
	private Integer id;
	private Integer adminId;
	private Integer materialtypeId;
	private String title;
	private String titlepicture;
	private String content;
	private Date postdate;
	private boolean status;
	private Set<File> files = new HashSet<File>(0);

}
