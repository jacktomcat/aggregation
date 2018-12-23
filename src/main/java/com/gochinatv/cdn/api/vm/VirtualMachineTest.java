package com.gochinatv.cdn.api.vm;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import java.util.Properties;

/**
 * ${DESCRIPTION}
 *
 * @auhtor jacktomcat
 * @create 2018-12-03 下午9:31
 */
public class VirtualMachineTest {


    public static void main(String[] args) {

        VirtualMachineDescriptor virtualMachineDescriptor = null;

        for (VirtualMachineDescriptor descriptor : VirtualMachine.list()) {
            String pid = descriptor.id();
            System.out.println(pid);
            if (pid.equals(Integer.toString(1782))) {
                virtualMachineDescriptor = descriptor;
            }

            try {
                VirtualMachine virtualMachine = VirtualMachine.attach(virtualMachineDescriptor);
                Properties systemProperties = virtualMachine.getSystemProperties();
                System.out.println(systemProperties);

                //virtualMachine.loadAgent("");
            } catch (AttachNotSupportedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
