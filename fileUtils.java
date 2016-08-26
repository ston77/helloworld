	/*　
	1.从旧文件拷贝内容到新文件 
	2.删除旧文件 
	@param oldPath the path+name of old file 
	@param newPath the path+name of new file 
	@throws Exception
	160826
	*/
	
	private void transferFile(String oldPath, String newPath) throws Exception {
		int byteread = 0;
		File oldFile = new File(oldPath);
		FileInputStream fin = null;
		FileOutputStream fout = null;
		try {
			if (oldFile.exists()) {
				fin = new FileInputStream(oldFile);
				fout = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = fin.read(buffer)) != -1) {
					log.info("byteread==" + byteread);
					fout.write(buffer, 0, byteread);
				}
				if(fout != null){
					fout.close();
				}
				if (fin != null) {
					fin.close();// 如果流不关闭,则删除不了旧文件
					delFile(oldFile);
				}
			} else {
				throw new Exception("需要转移的文件不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (fin != null) {
				fin.close();
			}
		}
	}

	/**
	 * 删除文件,只支持删除文件,不支持删除目录
	 * 
	 * @param file
	 * @throws Exception
	 */
	private void delFile(File files) throws Exception {
		File file = new File(FGlobal.TWFILEPATH+files.getName());
		if (!file.exists()) {
			throw new Exception("文件" + file.getName() + "不存在,请确认!");
		}
		if (file.isFile()) {
			if (file.canWrite()) {
				boolean del = file.delete();
				log.info(file.getName()+ " 删除"+(del==true?"成功！":"失败！"));
			} else {
				throw new Exception("文件" + file.getName() + "只读,无法删除,请手动删除!");
			}
		} else {
			throw new Exception("文件" + file.getName() + "不是一个标准的文件,有可能为目录,请确认!");
		}
	}
	
	// 日期格式处理
	public String dateToString(String format, Date date) {
		return new SimpleDateFormat(format).format(date);
	}

	// 日期格式处理
	public Date stringToDate(String partern, String date) throws ParseException {
		return new SimpleDateFormat(partern).parse(date);
	}
