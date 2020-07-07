../../apache-jmeter-5.2.1/apache-jmeter-5.2.1/bin/jmeter.sh -n -t imgpequena.jmx -l imagenp/resultados75.jtl -j imagenp/registro75.log -DTHREADS=75 -DRAMP_UP=25 -DDURATION=265 -DPERFMON="imagenp/perfResultados75.jtl"
sleep 30
../../apache-jmeter-5.2.1/apache-jmeter-5.2.1/bin/jmeter.sh -n -t imgpequena.jmx -l imagenp/resultados150.jtl -j imagenp/registro150.log -DTHREADS=150 -DRAMP_UP=50 -DDURATION=290 -DPERFMON="imagenp/perfResultados150.jtl"
sleep 30
../../apache-jmeter-5.2.1/apache-jmeter-5.2.1/bin/jmeter.sh -n -t imgpequena.jmx -l imagenp/resultados300.jtl -j imagenp/registro300.log -DTHREADS=300 -DRAMP_UP=100 -DDURATION=340 -DPERFMON="imagenp/perfResultados300.jtl"

sleep 60

../../apache-jmeter-5.2.1/apache-jmeter-5.2.1/bin/jmeter.sh -n -t imggrande.jmx -l imageng/resultados75.jtl -j imagenp/registro75.log -DTHREADS=75 -DRAMP_UP=25 -DDURATION=265 -DPERFMON="imageng/perfResultados75.jtl"
sleep 30
../../apache-jmeter-5.2.1/apache-jmeter-5.2.1/bin/jmeter.sh -n -t imggrande.jmx -l imageng/resultados150.jtl -j imagenp/registro150.log -DTHREADS=150 -DRAMP_UP=50 -DDURATION=290 -DPERFMON="imageng/perfResultados150.jtl"
sleep 30
../../apache-jmeter-5.2.1/apache-jmeter-5.2.1/bin/jmeter.sh -n -t imggrande.jmx -l imageng/resultados300.jtl -j imagenp/registro300.log -DTHREADS=300 -DRAMP_UP=100 -DDURATION=340 -DPERFMON="imageng/perfResultados300.jtl"

