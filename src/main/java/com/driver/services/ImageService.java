package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();

        image.setDescription(description);
        image.setDimensions(dimensions);

        Blog blog;


        blog = blogRepository2.findById(blogId).get();

        List<Image>imageList = blog.getImageList();
        imageList.add(image);
        blog.setImageList(imageList);
        image.setBlog(blog);
        blogRepository2.save(blog);

        return image;

    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        String [] arr = imageRepository2.findById(id).get().getDimensions().split("X");
        int area = Integer.parseInt(arr[0])*Integer.parseInt(arr[1]);
        int givenArea = Integer.parseInt(screenDimensions.substring(0, 1))*
                Integer.parseInt(screenDimensions.substring(2,3));

        if(givenArea == 0){
            return 0;
        }
        return area/givenArea;
    }
}
