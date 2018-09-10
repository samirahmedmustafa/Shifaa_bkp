package entity.domain;

import entity.domain.util.Helper;
import entity.domain.util.JsfUtil;
import entity.domain.util.PaginationHelper;
import facade.ClinicServiceFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.mail.MessagingException;
import javax.servlet.http.Part;

@Named("clinicServiceController")
@SessionScoped
public class ClinicServiceController implements Serializable {

    private Part image;
    private ClinicService current;
    private DataModel items = null;
    @EJB
    private facade.ClinicServiceFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ClinicServiceController() {
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    public int doUpload() throws MessagingException {
        if (!image.getSubmittedFileName().equals("")) {
            String imgName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + image.getSubmittedFileName();
            String fileFullPath = Helper.getAbsPath("services") + imgName;
            try {
                InputStream inputStream = image.getInputStream();
                File file = new File(fileFullPath);
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] buffer = new byte[4096];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, length);
                }
                fileOutputStream.close();
                inputStream.close();
            } catch (Exception e) {
                System.out.println("Unable to save file due to ......." + e.getMessage());
                return 1;
            }
            System.out.println(Helper.getAppPath("services") + imgName);
            fileFullPath = Helper.getAppPath("services") + imgName;
            current.setImage(fileFullPath);
        }
        return 0;
    }

    public ClinicService getSelected() {
        if (current == null) {
            current = new ClinicService();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ClinicServiceFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (ClinicService) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new ClinicService();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() throws MessagingException {
        if (0 == doUpload()) {
            try {
                getFacade().create(current);
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClinicServiceCreated"));
                return prepareCreate();
            } catch (Exception e) {
                JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                return null;
            }
        } else {
            System.out.println("create function ........... Service is not added.");
        }
        return "failed_to_create";
    }

    public String prepareEdit() {
        current = (ClinicService) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClinicServiceUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (ClinicService) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            if (current.getImage() != null) {
                System.out.println("Deleting " + current.getImage()
                        .replace(Helper.getAppPath("services"), Helper.getAbsPath("services")) + ".");
                new File(current.getImage()
                        .replace(Helper.getAppPath("services"), Helper.getAbsPath("services"))
                ).delete();
            }
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClinicServiceDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public ClinicService getClinicService(java.lang.Long id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = ClinicService.class)
    public static class ClinicServiceControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ClinicServiceController controller = (ClinicServiceController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "clinicServiceController");
            return controller.getClinicService(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ClinicService) {
                ClinicService o = (ClinicService) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ClinicService.class.getName());
            }
        }

    }

}
