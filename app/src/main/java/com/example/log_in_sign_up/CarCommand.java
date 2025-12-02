package com.example.log_in_sign_up;

public class CarCommand {

    private String targetSquare;   // المربع الذي تتحرك إليه السيارة (1 إلى 9)
    private String mode;           // وضع التشغيل: "manual" أو "auto"
    private String action;         // العملية: "move", "spray", "stop"
    private String time;  // الوقت أو التاريخ الذي أُرسل فيه الأمر
    private String imageUrl;

    // 🔹 لازم يكون في constructor فاضي عشان Firestore يعرف يبني الكائن
    public CarCommand() {}

    // 🔹 Constructor آخر لتعبئة البيانات بسهولة
    public CarCommand(String targetSquare, String mode, String action, String time, String imageUrl) {
        this.targetSquare = targetSquare;
        this.mode = mode;
        this.action = action;
        this.time = time;
        this.imageUrl = imageUrl;
    }

    // 🔹 Getters and Setters
    public String getTargetSquare() {
        return targetSquare;
    }

    public void setTargetSquare(String targetSquare) {
        this.targetSquare = targetSquare;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    // 🔹 طريقة مفيدة لعرض القيم عند الطباعة أو التصحيح
    @Override
    public String toString() {
        return "CarCommand{" +
                "targetSquare='" + targetSquare + '\'' +
                ", mode='" + mode + '\'' +
                ", action='" + action + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}


