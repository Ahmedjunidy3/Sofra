package com.example.sofra.utility;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ConvertFileToParts {

    public static MultipartBody.Part convertToMultiParts(String pathImageFile, String Key) {
        if (pathImageFile != null) {
            File file = new File(pathImageFile);
            RequestBody filePart = RequestBody.create(MediaType.parse("image/*"), file);
            return MultipartBody.Part.createFormData(Key, file.getName(), filePart);
        } else {
            return null;
        }
    }

    public static RequestBody convertToRequestBody(String partName) {
        try {
            if (!partName.equals("")) {
                return RequestBody.create(MediaType.parse("multipart/form-data"), partName);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
