/*��
	1.�Ӿ��ļ��������ݵ����ļ� 
	2.ɾ�����ļ� 
	@param oldPath the path+name of old file 
	@param newPath the path+name of new file 
	@throws Exception */
	
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
					fin.close();// ��������ر�,��ɾ�����˾��ļ�
					delFile(oldFile);
				}
			} else {
				throw new Exception("��Ҫת�Ƶ��ļ�������!");
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
	 * ɾ���ļ�,ֻ֧��ɾ���ļ�,��֧��ɾ��Ŀ¼
	 * 
	 * @param file
	 * @throws Exception
	 */
	private void delFile(File files) throws Exception {
		File file = new File(FGlobal.TWFILEPATH+files.getName());
		if (!file.exists()) {
			throw new Exception("�ļ�" + file.getName() + "������,��ȷ��!");
		}
		if (file.isFile()) {
			if (file.canWrite()) {
				boolean del = file.delete();
				log.info(file.getName()+ " ɾ��"+(del==true?"�ɹ���":"ʧ�ܣ�"));
			} else {
				throw new Exception("�ļ�" + file.getName() + "ֻ��,�޷�ɾ��,���ֶ�ɾ��!");
			}
		} else {
			throw new Exception("�ļ�" + file.getName() + "����һ����׼���ļ�,�п���ΪĿ¼,��ȷ��!");
		}
	}
	
	// ���ڸ�ʽ����
	public String dateToString(String format, Date date) {
		return new SimpleDateFormat(format).format(date);
	}

	// ���ڸ�ʽ����
	public Date stringToDate(String partern, String date) throws ParseException {
		return new SimpleDateFormat(partern).parse(date);
	}