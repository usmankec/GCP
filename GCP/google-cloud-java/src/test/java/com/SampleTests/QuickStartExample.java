package com.SampleTests;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageClass;
import com.google.cloud.storage.StorageOptions;

public class QuickStartExample {
	
	public static String getCredFileStr(){
		StringBuffer sb=new StringBuffer(File.separator);    
		sb.append("Users");
		sb.append(File.separator);
		sb.append("nagmalleswara_rayala");
		sb.append(File.separator);
		sb.append("Documents");
		sb.append(File.separator);
		sb.append("GCP");
		sb.append(File.separator);
		sb.append("google-cloud-java");
		sb.append(File.separator);
		sb.append("src");
		sb.append(File.separator);
		sb.append("main");
		sb.append(File.separator);
		sb.append("java");
		sb.append(File.separator);
		sb.append("config");
		sb.append(File.separator);
		sb.append("serviceaccountkey.json");
		return sb.toString();
		
	}
	public static void bucketsList(Storage storage) throws Exception
	{
		System.out.println("Buckets:");
		  Page<Bucket> buckets = storage.list();
		  for (Bucket bucket : buckets.iterateAll()) {
		    System.out.println(bucket.toString());
		  }
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		//export GOOGLE_APPLICATION_CREDENTIALS="/Users/nagmalleswara_rayala/Documents/GCP/google-cloud-java/src/main/java/config/serviceaccountkey.json"
		
		//Storage storage=StorageOptions.getDefaultInstance().getService();
		//System.out.println(""+storage);
		String project_id="poc-sed-casb-testenv";
		
		//That is an error related to IAM permissions. You will have to grant the appropriate permissions to the service account you are using to authenticate with @google-cloud/storage. The IAM page in the console is here: https://console.cloud.google.com/iam-admin/iam/project
		
		
		StorageOptions storageOptions = StorageOptions.newBuilder()
			       .setProjectId(project_id)
			        .setCredentials(GoogleCredentials.fromStream(new 
			         FileInputStream(getCredFileStr()))).build();
		Storage storage = storageOptions.getService();
		
		
		//Storage storage = StorageOptions.newBuilder().setProjectId(project_id).build().getService();
		System.out.println(""+storage.getOptions().getHost());
		System.out.println(""+storage.getOptions().getProjectId());
		System.out.println(""+storage.getOptions().getCredentials());
		
		
		bucketsList(storage);

		StorageClass storageClass=StorageClass.COLDLINE;
		
		
		String location = "us";
		
		String bucketName = "audit-qaautomation_2";

	    Bucket bucket =
	        storage.create(
	            BucketInfo.newBuilder(bucketName)
	                .setStorageClass(storageClass)
	                .setLocation(location)
	                .build());

	    System.out.println(
	        "Created bucket "
	            + bucket.getName()
	            + " in "
	            + bucket.getLocation()
	            + " with storage class "
	            + bucket.getStorageClass());


		

	}

}
