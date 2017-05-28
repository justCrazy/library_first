package com.web.library.domain;

public class Password {

	private String username;  //根据名字查询密码
	private boolean same;   //判断原密码是否一致
	public boolean isSame() {
		return same;
	}
	public void setSame(boolean same) {
		this.same = same;
	}
	private String oldPassword;
	private String newPassword1;
	private String newPassword2;
	
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	
	
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public String getNewPassword1() {
		return newPassword1;
	}
	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}
	public String getNewPassword2() {
		return newPassword2;
	}
	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}
	@Override
	public String toString() {
		return "Password [username=" + username + ", same=" + same + ", oldPassword=" + oldPassword + ", newPassword1="
				+ newPassword1 + ", newPassword2=" + newPassword2 + "]";
	}
	
	
	
	
}
