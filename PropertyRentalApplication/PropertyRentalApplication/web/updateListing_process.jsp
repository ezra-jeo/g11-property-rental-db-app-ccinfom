

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Listing</title>
    </head>
    <body>
            <jsp:useBean id="property" class="propertyRental.Property" scope="session"/>
            <% 
                int v_propertyListingID = Integer.parseInt(request.getParameter("propertyListingID"));
                int v_hostID = Integer.parseInt(request.getParameter("hostID")); 
                String v_listingName = request.getParameter("listingName");       
                String v_description = request.getParameter("description");       
                double v_pricePerNight = Double.parseDouble(request.getParameter("pricePerNight")); 
                String v_street = request.getParameter("street");                 
                String v_city = request.getParameter("city");                     
                String v_province = request.getParameter("province");             
                String v_country = request.getParameter("country");               
                String v_status = request.getParameter("status");                 
                
                int status = property.updateListing(
                    v_propertyListingID,
                    v_hostID,   
                    v_listingName,                      
                    v_description,                      
                    v_pricePerNight,                    
                    v_street,                           
                    v_city,                             
                    v_province,                         
                    v_country,                          
                    v_status                           
                );
               
                if (status == 1) {
                    out.println("<h2>Update Successful</h2><br>"); 
                }
                else {
                    out.println("<h2>Update Unsuccessful</h2><br>");
                }
            %>
            <a href="host_main.jsp">
                <button type="button">Back</button>
            </a>
    </body>
</html>
