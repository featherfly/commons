package cn.featherfly.common.bean.vo;

public class ClassRoom extends Room<Student> {

    private Integer size;

    /**
     * 返回size
     * 
     * @return size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * 设置size
     * 
     * @param size
     *            size
     */
    public void setSize(Integer size) {
        this.size = size;
    }

}
